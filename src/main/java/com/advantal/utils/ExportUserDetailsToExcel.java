package com.advantal.utils;

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

import com.advantal.model.User;

public class ExportUserDetailsToExcel {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<User> userList;

	public ExportUserDetailsToExcel(List<User> userList) {
		this.userList = userList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("User");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Sr.", style);
		createCell(row, 1, "Name", style);
		createCell(row, 2, "Email", style);
		createCell(row, 3, "CountryCode", style);
		createCell(row, 4, "PhoneNo", style);
		createCell(row, 5, "DeviceType", style);
		createCell(row, 6, "ImageUrl", style);
		createCell(row, 7, "CreationDate", style);
		createCell(row, 8, "UpdationDate", style);
		createCell(row, 9, "Status", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
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
		font.setFontHeight(14);
		style.setFont(font);
		int i = 0;
		for (User user : userList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			i = i + 1;
			createCell(row, columnCount++, i, style);
			createCell(row, columnCount++,
					user.getName() != null && !user.getName().isBlank() ? user.getName() : "Nill", style);
			createCell(row, columnCount++,
					user.getEmail() != null && !user.getEmail().isBlank() ? user.getEmail() : "Nill", style);
			createCell(row, columnCount++, user.getCountryCode(), style);
			createCell(row, columnCount++, user.getPhoneNo(), style);
			createCell(row, columnCount++, user.getDeviceType(), style);
			createCell(row, columnCount++,
					user.getImageUrl() != null && !user.getImageUrl().isBlank() ? user.getImageUrl() : "Nill", style);
			createCell(row, columnCount++, DateUtil.convertDateToStringDateTime(user.getCreationDate()), style);
			if (user.getUpdationDate() != null) {
				createCell(row, columnCount++,DateUtil.convertDateToStringDateTime(user.getUpdationDate()), style);
			} else {
				createCell(row, columnCount++, "0000-00-00 00:00:00", style);
			}
			if (user.getStatus().equals(Constant.ONE)) {
				createCell(row, columnCount++, "Active", style);
			} else if (user.getStatus().equals(Constant.TWO)) {
				createCell(row, columnCount++, "Blocked", style);
			} else if (user.getStatus().equals(Constant.THREE)) {
				createCell(row, columnCount++, "Not Verified", style);
			}
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
