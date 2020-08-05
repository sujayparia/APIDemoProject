package com.api.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.*;

public class TestBase {
	
	public static Properties prop;
	
	public static void baseURISetup() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop = new Properties();
		prop.load(fis);
		
		RestAssured.baseURI=prop.getProperty("BASEURI");
	
		
	}

}
