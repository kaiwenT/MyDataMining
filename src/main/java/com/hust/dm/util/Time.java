package com.hust.dm.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class Time {


	public static String convert(Cell cell){
		if (cell == null)
			return "1900-01-01 00:00:00";
		if (DateUtil.isCellDateFormatted(cell)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()))
					.toString();
		}
		return new DecimalFormat("#").format(cell.getNumericCellValue());
	}
}
