package com.excelservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.excelservice.util.Configuration;
import com.excelservice.util.PropertyReader;

import au.com.bytecode.opencsv.CSVReader;

public class JSONToExcel {
	final static Logger logger = LogManager.getLogger(JSONToExcel.class);
	private static final String DOT_CSV = ".csv";
	private static final String DOT_XLS = ".xls";
	private static final String JSON_DATA = "data";
	
	public JSONToExcel(){
		//load config4rest.properties
		try {
			Configuration.setConfigurator("config4rest.properties");
		} catch (Exception e) {
			logger.error("MRT-ERROR:"+e.getMessage());		
			return;
		}
	}

	File excelFile;

	@SuppressWarnings("deprecation")
	public String getJSONCSV(String jsonString) {
		String filePath = null;
		FileWriter file = null;
		JSONObject output;
		try {
			JSONParser parser = new JSONParser();
			output = (JSONObject) parser.parse(jsonString);

			JSONArray jsonData = (JSONArray) output.get(JSON_DATA);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String tempPath = PropertyReader.getTmpExcelPath();
			String csvString = new String();
			if(tempPath != null && timeStamp != null){
				file = new FileWriter(tempPath+timeStamp+DOT_CSV);
				String csvRows = new String();
				for (int i = 0; i < jsonData.size(); i++){
					JSONObject iteratedObj = (JSONObject) jsonData.get(i);
					csvString = iteratedObj.keySet().toString();
					csvString = csvString.replaceAll("\\s+",""); 
					csvString = csvString.substring(1);// removed 1st character i.e. '['
					csvString = csvString.substring(0, csvString.length() - 1);//removed last character i.e. ']'
					csvString = csvString+ "\n";//added new line
					
					for (Object object : iteratedObj.keySet()) {
						String val = new String();
							if(iteratedObj.get(object)==null){
								val = "null" + ",";
							}
							else{
								val = iteratedObj.get(object).toString().trim() + ",";
							}
							csvRows = csvRows + val;
						}
					csvRows = csvRows.substring(0, csvRows.length() - 1);//removed last character i.e. ','
					csvRows = csvRows+ "\n";//added new line
					}
				csvString = csvString + csvRows;
					try {
						file.append(csvString);
						filePath = tempPath+timeStamp+DOT_CSV;

					} catch (IOException e) {
						e.printStackTrace();
						logger.error("MRT-ERROR:"+e);
					}
					finally {
						try {
							file.flush();
							file.close();
							} catch (IOException e) {
								logger.error("MRT-ERROR: Error while flushing/closing fileWriter !!!");
							e.printStackTrace();
							logger.error("MRT-ERROR:"+e);
							}
				}
					
				}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:"+e);
		}
		return filePath;
	}

	public String createExcelFromCSV(String inputCSVFile) {

		/* Step -1 : Read input CSV file in Java */
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(inputCSVFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			logger.error("MRT-ERROR:"+e1);
		}
		/* Variables to loop through the CSV File */
		String[] nextLine; /* for every line in the file */
		int lnNum = 0; /* line number */
		/* Step -2 : Define POI Spreadsheet objects */
		HSSFWorkbook new_workbook = new HSSFWorkbook(); // create a blank
														// workbook object
		HSSFSheet sheet = new_workbook.createSheet(PropertyReader.getSheetName()); // create a
																// worksheet
																// with caption
																// score_details
		/* Step -3: Define logical Map to consume CSV file data into excel */
		// Map<String, Object[]> excel_data = new HashMap<String, Object[]>();
		// create a map and define data
		Map<String, String[]> excel_data = new TreeMap<String, String[]>(); // create
																			// a
																			// map
																			// and
																			// define
																			// data

		/* Step -4: Populate data into logical Map */
		try {
			while ((nextLine = reader.readNext()) != null) {
				lnNum++;
				//if (nextLine.length == 6 ||nextLine.length == 10) {
					excel_data.put(Integer.toString(lnNum), nextLine);
				//}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("MRT-ERROR:"+e1);
		}
		/* Step -5: Create Excel Data from the map using POI */
		Set<String> keyset = excel_data.keySet();
		List<String> list = new ArrayList<String>();
		list.addAll(keyset);
		List<String> sortedList = getSortedList(list);
		int rownum = 0;
		
		CellStyle cellStyle = new_workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setWrapText(true);
		Font font = new_workbook.createFont();
		font.setColor(HSSFColor.BLUE_GREY.index);
		font.setFontName(PropertyReader.getFontName());// font.setFontHeightInPoints((short)24);
		cellStyle.setFont(font);
		
		for (Object key : sortedList) { // loop through the data and add them to the
									// cell
			Row row = sheet.createRow(rownum++);
			Object[] objArr = excel_data.get(key.toString());
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				} else {
					cell.setCellValue((String) obj);
					cell.setCellStyle(cellStyle);
				}
			}
		}
		/* Write XLS converted CSV file to the output file */
		FileOutputStream output_file = null;
		String excelFilePath = null;
		try {
			excelFilePath = inputCSVFile.replace(DOT_CSV, DOT_XLS);
			
			output_file = new FileOutputStream(new File(excelFilePath));
			new_workbook.write(output_file);// write converted XLS file to
											// output stream
			output_file.close(); // close the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:"+e);
		} // create XLS file
		catch (IOException e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:"+e);
		}
		return excelFilePath;

	}
	
	public boolean deleteFile(String filePath) {
		boolean isDeleted = false;
		try {

			File file = new File(filePath);

			if (file.delete()) {
				isDeleted = true;
				System.out.println(file.getName() + " is deleted!");
				logger.warn("MRT-INFO:" + "Delete operation is successful for file : '" + filePath + "'");
			} else {
				System.out.println("Delete operation is failed.");
				logger.warn("MRT-WARN:" + "Delete operation is failed for file : '" + filePath + "'");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:" + e);
		}
		return isDeleted;

	}
	
	public List<String> getSortedList(List<String> strings){

	    Collections.sort(strings, new Comparator<String>() {
	        public int compare(String o1, String o2) {
	            return extractInt(o1) - extractInt(o2);
	        }

	        int extractInt(String s) {
	            String num = s.replaceAll("\\D", "");
	            // return 0 if no digits found
	            return num.isEmpty() ? 0 : Integer.parseInt(num);
	        }
	    });
		return strings;
	
	} 
	

}
