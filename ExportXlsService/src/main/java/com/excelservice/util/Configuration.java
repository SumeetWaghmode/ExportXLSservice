package com.excelservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Configuration {
	public static Properties config = new Properties();
	final static Logger logger = LogManager.getLogger(Configuration.class);
	
	public static void setConfigurator(String propertiesFileName) 
			throws FileNotFoundException,IOException {
		File configDir = new File( System.getenv("CATALINA_HOME"), "conf");
		File configFile = new File(configDir, propertiesFileName);
		InputStream stream;
		try {
			stream = new FileInputStream(configFile);
			config.load(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("MRT-ERROR:"+e.getMessage());
		}
		
		
			}
		
	}

