package com.mentor.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.base.TestBase;

public class WritingDataToExcel extends TestBase{
	
	public static void main(String args[]) throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/excel/My-Test-sheet-data.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Sheet 1");
		log.debug("sheet created");
		XSSFRow row = sheet.createRow(1);
		log.debug("row created");

		XSSFCell cell = row.createCell(1);
		log.debug("Cell created");

//		cell.setCellType();
		cell.setCellValue("This is my first entry");
		log.debug("Value created");

		fis.close();
		workbook.close();
		System.out.println("End of the Excell writing");
	}

}
