package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String userName, String password) {
		logger.info("Login with UserName: " + userName);
		
		LoginPage lPage = new LoginPage(driver);
		
		lPage.setUserName(userName);
		lPage.setPassword(password);
		
		lPage.clickSubmit();
		
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.info("Login was failed");
		}
		else if (driver.getTitle().equals("Guru99 Bank Manager HomePage")){
			Assert.assertTrue(true);
			lPage.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Login was successful");
		}
		
	}
	
	
	
	
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colNum = XLUtils.getCellCount(path, "Sheet1", 1);
	
		
		String loginData[][] = new String[rowNum][colNum];
		
		for (int i=1; i<=rowNum; i++) {
			for (int j=0; j<colNum; j++) {
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return loginData;
	}
	
}
