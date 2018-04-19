package com.excelservice.rest;


import java.io.File;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.excelservice.JSONToExcel;

 
@Path("/json/excel")
public class JSONService{

	//final static Logger logger = LogManager.getLogger(JSONService.class);
	final static Logger logger = Logger.getLogger(JSONService.class);
	
 	private JSONToExcel json=new JSONToExcel();
 
	@POST
	@Path("/post")
	@Produces("application/excel")
	public Response createTrackInJSON(String jsonString) {
		
		//Mqtt Client
		// Create an Mqtt client
		//Results
		
		logger.warn("MRT-INFO: Connected to REST Service for export excel....... ");
		
		logger.warn("MRT-INFO: Input JSON string from frontend: " + jsonString);
		
		String excelOutput = null;
		String csvFilePath=json.getJSONCSV(jsonString); 
		if(csvFilePath!=null)
		{
			excelOutput=json.createExcelFromCSV(csvFilePath);
		}
		
		ResponseBuilder response = null;
		if(excelOutput != null){
		File file = new File(excelOutput);
		
		response = Response.ok((Object) file);
	    logger.warn("MRT-INFO: File generated :" + file.getName());
		setAccessControlHeaders(response, file.getName());
		}
		
		logger.warn("MRT-INFO: Exits from REST Service for export excel....... ");
		return response.build();
		
	}
	
	private void setAccessControlHeaders(ResponseBuilder resp, String fileName) {
	       resp.header("Content-Disposition",
				"attachment; filename="+fileName);
	       resp.header("Access-Control-Allow-Origin", "*");
	       resp.header("Access-Control-Allow-Credentials", "true");
	       resp.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
	       resp.header("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token, X-Requested-With, Authorization");
	   }
	
	
}