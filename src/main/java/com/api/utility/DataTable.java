package com.api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable {

	public static String getData(String testcase, String column) {

		String data=null;

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\API_TestData.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("TestData");

			int finalRowIndex=0;
			int finalColumnIndex=0;

			for (int currentRow=1; currentRow<=sheet.getLastRowNum();currentRow++) {
				Row row = sheet.getRow(currentRow);
				Cell cell = row.getCell(1);
				String currentValue = cell.getStringCellValue();

				if (currentValue.equalsIgnoreCase(testcase)) {
					finalRowIndex = currentRow;
					break;
				}
			}

			Row firstRow = sheet.getRow(0);

			for (int currentCol=0; currentCol<=firstRow.getLastCellNum();currentCol++) {
				Cell columnName = firstRow.getCell(currentCol);
				String currentColValue = columnName.getStringCellValue();

				if (currentColValue.equalsIgnoreCase(column)) {
					finalColumnIndex = currentCol;
					break;
				}

			}
			
			data = sheet.getRow(finalRowIndex).getCell(finalColumnIndex).getStringCellValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	
	public static void updateData(String testcase, String column, String value) {
		

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\API_TestData.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("TestData");
			

			int finalRowIndex=0;
			int finalColumnIndex=0;
			Cell destinationCell=null;

			for (int currentRow=1; currentRow<=sheet.getLastRowNum();currentRow++) {
				Row row = sheet.getRow(currentRow);
				Cell cell = row.getCell(1);
				String currentValue = cell.getStringCellValue();

				if (currentValue.equalsIgnoreCase(testcase)) {
					finalRowIndex = currentRow;
					break;
				}
			}

			Row firstRow = sheet.getRow(0);

			for (int currentCol=0; currentCol<=firstRow.getLastCellNum();currentCol++) {
				Cell columnName = firstRow.getCell(currentCol);
				String currentColValue = columnName.getStringCellValue();

				if (currentColValue.equalsIgnoreCase(column)) {
					finalColumnIndex = currentCol;
					break;
				}

			}
			destinationCell=sheet.getRow(finalRowIndex).getCell(finalColumnIndex);
			destinationCell.setCellValue(value);
			
			fis.close();
			
			FileOutputStream outFile = new FileOutputStream(new File(System.getProperty("user.dir")+"\\TestData\\API_TestData.xlsx"));
		    workbook.write(outFile);
		    outFile.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
}
}
