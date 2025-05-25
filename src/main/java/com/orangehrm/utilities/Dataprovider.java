package com.orangehrm.utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	private static final String File_Path = System.getProperty("user.dir")
			+ "/src/test/resources/testdata/TestData.xlsx";

	@DataProvider(name = "ValidLoginData")
	public static Object[][] ValidLoginData() {

		return getSheetData("ValidLoginData");
	}

	@DataProvider(name = "InValidLoginData")
	public static Object[][] InValidLoginData() {

		return getSheetData("InValidLoginData");
	}

	@DataProvider(name="Emp_Data")
	public static  Object[][] empVerification() {
		return getSheetData("Emp_Data");
	}
	
	private static Object[][] getSheetData(String sheetName) {

		List<String[]> sheetData = ExcelReader.getSheetdata(File_Path, sheetName);

		Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];

		for (int i = 0; i < sheetData.size(); i++) {
			data[i] = sheetData.get(i);

		}

		return data;
	}

}
