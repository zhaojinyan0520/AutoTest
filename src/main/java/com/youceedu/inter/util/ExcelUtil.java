package com.youceedu.inter.util;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/**
	 * 创建全局变量
	 */
	private String filePath = "";
	
	/**
	 * 创建构造方法
	 */
	public ExcelUtil(String filePath ){
		this.filePath = filePath;
	}
	
	/**
	 * 读取文件，获取Excel
	 */
	public Workbook getwb(){
		Workbook wb = null;
		try {
			InputStream input = new FileInputStream(filePath);
			if (filePath.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(input);
			}else {
				wb = new HSSFWorkbook(input);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wb;
		
	}
	
	/**
	 * 获取Excel的sheet下标
	 */
	
	public Sheet getsheet(int sheetnum){
		Sheet sheet = null;
		try {
			Workbook wb = getwb();
			sheet = wb.getSheetAt(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sheet;
		
	}
	
	/**
	 * 获取单个行和列的单元格
	 */
	public Object getcellobjectvalue(int sheetNum,int rowNum,int cellNum){
		Object result = null;
		Row row = getsheet(sheetNum).getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		result = getcellFromValue(cell);
		return result;
		
	}
	
	/**
	 * 获取多个行和列的单元格
	 */
	public Object[][] getArrayCellValue(int sheetNum){
		Object[][] testCaes = null;
		try {
			//获取最后一行的值
			int LastRowNum = getsheet(sheetNum).getLastRowNum();
			//得到object[][]对象
			testCaes = new Object[LastRowNum][10];
			
			for (int rowIndex = 1; rowIndex <= LastRowNum; rowIndex++) {
				Row row = getsheet(sheetNum).getRow(rowIndex);
				if (row==null) {
					continue;
				}
				for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					if (cell==null) {
						testCaes[rowIndex-1][cellIndex]="";
					}else {
						testCaes[rowIndex-1][cellIndex]=getcellFromValue(cell);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testCaes;
		
	}
	
	/**
	 * 判断列的值
	 */
	public Object getcellFromValue(Cell cell){
		
		Object value=null;
		try {
			if (cell.getCellType()==cell.CELL_TYPE_BLANK) {
				value = "";
			}else if (cell.getCellType()==cell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue();
			}else if (cell.getCellType()==cell.CELL_TYPE_FORMULA) {
				value = cell.getCellFormula();
			}else if (cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
				value = cell.getNumericCellValue();
			}else if (cell.getCellType()==cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue().trim();
			}else {
				value = cell.getDateCellValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
		
	}
}
