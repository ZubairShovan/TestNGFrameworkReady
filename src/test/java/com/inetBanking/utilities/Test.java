package com.inetBanking.utilities;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colNum = XLUtils.getCellCount(path, "Sheet1", 1);
		
		System.out.println(rowNum + " AND " + colNum);
		
		String loginData[][] = new String[rowNum][colNum];
		
		for (int i=1; i<=rowNum; i++) {
			for (int j=0; j<colNum; j++) {
				System.out.println(XLUtils.getCellData(path, "Sheet1", i, j));
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		System.out.println("L O G I N D A T A");

		for (int i=0; i<rowNum; i++) {
			for (int j=0; j<colNum; j++) {
				System.out.println(loginData[i][j]);
			}
		}
		
		

	}

}
