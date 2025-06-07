package com.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static List<String[]> getSheetdata(String filePath, String sheetName) {

//		data are stored in a list and created in a variable
		List<String[]> data = new ArrayList<>();

//		getting excel file and sheet
		try (FileInputStream fis = new FileInputStream(filePath); Workbook wb = new XSSFWorkbook(fis)) {
			Sheet sh = wb.getSheet(sheetName);
			if (sh == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " is not exist !!!");
			}

//			retrieve data from sheet , Row iteration
			for (Row row : sh) {
				if (row.getRowNum() == 0) {
					continue;
				}
//				Reading the cell data
				List<String> rowData = new ArrayList<>();

				for (Cell cell : row) {
					rowData.add(getCellvalue(cell));
				}
//				convert rowData to String[]
				data.add(rowData.toArray(new String[0]));

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return data;

	}

	private static String getCellvalue(Cell cell) {

		if (cell == null) {
			return " ";
		}

		switch (cell.getCellType()) {
		
		case STRING:
			return cell.getStringCellValue();
			
		case NUMERIC:

			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			}
			
			return String.valueOf((int) cell.getNumericCellValue());

		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		default:
			return " ";
		}

	}
}
