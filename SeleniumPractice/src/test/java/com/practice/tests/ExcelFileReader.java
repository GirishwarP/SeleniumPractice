package com.practice.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
	File file;
	Workbook workbook = null;
	Sheet sheet;
	
	public List<ExcelData> readExcel(String filePath,String fileName,String sheetName) throws Exception {
		List<ExcelData> excelData;
		file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			workbook = new XSSFWorkbook(fis);
		}else if(fileExtension.equals(".xls")) {
			workbook = new HSSFWorkbook(fis);
		}
		
		sheet = workbook.getSheet(sheetName); 
		int totalRows = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		excelData = new ArrayList<ExcelData>();
		for(int i=1; i<=totalRows; i++) {
			Row row = sheet.getRow(i);
				
			ExcelData data = new ExcelData();
			
			data.setUserName(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());
			data.setPassword(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());
			data.setEmail(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());
			data.setRowId(row.getRowNum());
			
			excelData.add(data);
		}
		return excelData;
	}
	
	public void writeExcel(String filePath,String fileName,String sheetName, String status,Integer rowId) throws IOException {
		
		file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			workbook = new XSSFWorkbook(fis);
		}else if(fileExtension.equals(".xls")) {
			workbook = new HSSFWorkbook(fis);
		}
		
		sheet = workbook.getSheet(sheetName); 
		
		Row row = sheet.getRow(rowId);
		 
		row.createCell(3, CellType.STRING).setCellValue(status);
		
		file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
	}
}
