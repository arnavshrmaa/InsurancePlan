package com.example.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetGenerator {

	//For Creating Excel sheet report (XSSFWorkbook)
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Plan> plans;

	private void writeHeaderLine() {
		
		//For Data Formating
		sheet = workbook.createSheet("Plan");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		style.setFont(font);

		createCell(row, 0, "id", style);
		createCell(row, 1, "PlanName", style);
		createCell(row, 2, "PlanCategory", style);
		createCell(row, 3, "PlanStartDate", style);
		createCell(row, 4, "PlanValidity", style);
	}

	//Creating Cells in Excel Sheet Report
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		style.setFont(font);
		for (Plan plan : plans) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
 
			createCell(row, columnCount++, plan.getId(), style);
			createCell(row, columnCount++, plan.getPlanName().toString(), style);
			createCell(row, columnCount++, plan.getPlanCategory().toString(), style);
			createCell(row, columnCount++, plan.getPlanStartDate().toString(), style);
			createCell(row, columnCount++, plan.getPlanValidity().toString(), style);
 		}
	}

	
	public void export(HttpServletResponse rs, List<Plan> plans) throws IOException {
		this.plans = plans;
		this.workbook = new XSSFWorkbook();
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = rs.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
