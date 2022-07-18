package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void loginTest() throws IOException {
		
		logger.info("URL is opened.");
		
		LoginPage lPage = new LoginPage(driver);
		
		lPage.setUserName(userName);
		lPage.setPassword(userpassword);
		lPage.clickSubmit();
		
		logger.info(driver);
		
		System.out.println(driver.getTitle());
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("LoginTest passed");
		}
		else {
			captureScreenshot("loginTest");
			logger.info("LoginTest failed");
			Assert.assertTrue(false);
		}
	}
	
}
