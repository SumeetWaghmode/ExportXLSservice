package com.excelservice.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class PropertyReader {

	public PropertyReader(String configFilePath) {
		super();
		
		// Initiate property configurator
		try {
			Configuration.setConfigurator(configFilePath);
		} catch (Exception e) {
			logger.error(e);
			logger.error("\nMRT-ERROR: Unable to read Configuration file.");			
			return;
		}   
		
	}

	public static String getTmpExcelPath(){
		String tmpExcelPath = Configuration.config.getProperty("EXCEL_TMP_LOCATION");
		if(tmpExcelPath == null || tmpExcelPath.isEmpty()){
			logger.error("MRT-INFO: The system variable \"EXCEL_TMP_LOCATION\" is not set in environment variable or is empty");
			tmpExcelPath = Configuration.config.getProperty("EXCEL_TMP_LOCATION");
			logger.error("MRT-INFO: Using \"EXCEL_TMP_LOCATION\" from config4rest.properties file: "+tmpExcelPath);
		}
		return tmpExcelPath;
	}
	
	public static String getSheetName(){
		String sheetName = Configuration.config.getProperty("SHEET_NAME");
		if(sheetName == null || sheetName.isEmpty()){
			logger.error("MRT-INFO: The system variable \"SHEET_NAME\" is not set in environment variable or is empty");
			sheetName = Configuration.config.getProperty("SHEET_NAME");
			logger.error("MRT-INFO: Using \"SHEET_NAME\" from config4rest.properties file: "+sheetName);
		}
		return sheetName;
	}
	
	public static String getFontName(){
		String fontName = Configuration.config.getProperty("FONT_NAME");
		if(fontName == null || fontName.isEmpty()){
			logger.error("MRT-INFO: The system variable \"FONT_NAME\" is not set in environment variable or is empty");
			fontName = Configuration.config.getProperty("FONT_NAME");
			logger.error("MRT-INFO: Using \"FONT_NAME\" from config4rest.properties file: "+fontName);
		}
		return fontName;
	}	
	
	final static Logger logger = LogManager.getLogger(PropertyReader.class);
}
