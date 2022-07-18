package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;


public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationURL();
	public String userName = readConfig.getUserName();
	public String userpassword = readConfig.getPassword();
	public static WebDriver driver;
	public Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		logger = Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		

//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		
		
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
			driver = new FirefoxDriver();
		}
		else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIePath());
			driver = new InternetExplorerDriver();
		}
		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		logger.info("SET UP");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("TEAR DOWN");
	}
	
	
	public void captureScreenshot(String fileName) throws IOException {
		System.out.println("SCREENSHOT");
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File sourceFile = tScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(readConfig.getScreenshotPath()+fileName+".png");
//		File targetFile = new File(System.getProperty("user.dir") + "/Screenshots/" + fileName + ".png");
		FileUtils.copyFile(sourceFile, targetFile);
		logger.info("Screenshot " + fileName +".png" + " was taken.");
	}
	
	
	public boolean isAlertPresent() {
		
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
		
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
