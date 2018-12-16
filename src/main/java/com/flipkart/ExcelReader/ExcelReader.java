package com.flipkart.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path ;
	public FileInputStream fis ;
	public XSSFWorkbook workbook ;
	public XSSFSheet sheet ;
	public XSSFRow row ;
	public XSSFCell cell ;
	
	public ExcelReader(String path) {
		this.path = path ;
		
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public String[][] getDataFromSheet(String excelName,String sheetName) {
		
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()+1 ;
		int colCount = sheet.getRow(0).getLastCellNum();
		
		String[][] dataSets = new String[rowCount-1][colCount];
		
		for(int i=1; i<rowCount; i++) {
			row = sheet.getRow(i);
			for(int j=1; j<colCount; j++) {
				cell = row.getCell(j);
				
				if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN) {
					dataSets[i][j] = String.valueOf(cell.getBooleanCellValue());
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
					dataSets[i][j]= String.valueOf(cell.getNumericCellValue());
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
					dataSets[i][j]= cell.getStringCellValue();
				}
			}
		}
		return dataSets ;
	}
}
