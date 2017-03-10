package com.hust.dm.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManage {
	  public static List<String[]> read(InputStream inputStream) throws FileNotFoundException, IOException {
	        List<String[]> list = new ArrayList<String[]>();
	        Workbook workbook;
	        workbook = new XSSFWorkbook(inputStream);
	        Sheet sheet = workbook.getSheetAt(0);
	        int rowNum = sheet.getLastRowNum();
	        int colNum = sheet.getRow(0).getLastCellNum();
	        for (int i = 1; i <= rowNum; i++) {
	            String[] rowStr = new String[colNum];
	            for (int j = 0; j < colNum; j++) {
	                try {
	                    Cell cell = sheet.getRow(i).getCell(j);
	                    if (cell.getCellType() == 0) {
	                        rowStr[j] = Time.convert(cell);
	                    } else {
	                        rowStr[j] = cell.toString();
	                    }
	                } catch (Exception e) {
	                    rowStr[j] = "";
	                }
	            }
	            list.add(rowStr);
	        }
	        workbook.close();
	        return list;
	    }
	  
	  public static void main(String[] args) {
		ExcelManage em = new ExcelManage();
		File file = new File("E:\\test.xlsx");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			List<String[]> list = em.read(inputStream);
			for (int i = 0; i < list.size(); i++) {
				String[] strs = list.get(i);
				for(String s : strs){
					System.out.print(s+" ");					
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

