package com.nttdata.casestudy.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBProperties {
	private Properties props = new Properties();
	String fileName ="C:\\Users\\Bodha Bhargav\\Mini Casestudy\\OrderCaseStudy\\src\\resoruces\\dbproperties";

	public DBProperties() {		
		try (FileInputStream fin = new FileInputStream(fileName)) {
			props.load(fin);
		}catch(IOException ie) {
			System.out.println("Exception while loading properties: " + ie);
		}
	}

	public String getdbURL() {
		return props.getProperty("dbUrl");
	}

	public String getUserId() {
		return props.getProperty("userId");
	}

	public String getPassword() {
		return props.getProperty("password");
	}

	public String getDriverName() {
		return props.getProperty("driverName");
	}
}
	
