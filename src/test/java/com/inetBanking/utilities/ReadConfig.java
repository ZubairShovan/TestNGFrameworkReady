package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	private Properties properties;
	
	public ReadConfig() {
		File srcFile = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fileInputStream = new FileInputStream(srcFile);
			properties = new Properties();
			properties.load(fileInputStream);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		return properties.getProperty("baseURL");
	}
	
	public String getUserName() {
		return properties.getProperty("username");
	}
	
	public String getPassword() {
		return properties.getProperty("password");
	}
	
	public String getChromePath() {
		return properties.getProperty("chromepath");
	}
	
	public String getIePath() {
		return properties.getProperty("iepath");
	}
	
	public String getFireFoxPath() {
		return properties.getProperty("firefoxpath");
	}
	
	public String getScreenshotPath() {
		return properties.getProperty("screenshotpath");
	}
}
