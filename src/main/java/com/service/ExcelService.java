package com.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	
	public byte[] generateDetailsProductExcel(List<String[]> datas) {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int cont = 1;
		
		XSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("DATA");
		rowhead.createCell(1).setCellValue("LOJA");
		rowhead.createCell(2).setCellValue("PROJETO");
		rowhead.createCell(3).setCellValue("PRODUTO");
		rowhead.createCell(4).setCellValue("PREÇO");
		rowhead.createCell(5).setCellValue("ESTOQUE");
		rowhead.createCell(6).setCellValue("VALIDADE");
		rowhead.createCell(7).setCellValue("RUPTURA");

		for(String[] data : datas) {
			XSSFRow row = sheet.createRow((short) cont++);
            row.createCell(0).setCellValue(data[0]);
            row.createCell(1).setCellValue(data[1]);
            row.createCell(2).setCellValue(data[2]);
            row.createCell(3).setCellValue(data[3]);
            row.createCell(4).setCellValue(data[4]);
            row.createCell(5).setCellValue(data[5]);
            row.createCell(6).setCellValue(data[6]);
            row.createCell(7).setCellValue(data[7]);
		}
		
		try {
			workbook.write(bos);
			workbook.close();
		}catch (Exception e) {
		}
		return bos.toByteArray();
	}
    
	public byte[] generateRupturaExcel(List<String[]> datas) {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int cont = 1;
		
		
		XSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("DATA");
		rowhead.createCell(1).setCellValue("LOJA");
		rowhead.createCell(2).setCellValue("PRODUTO");
		rowhead.createCell(3).setCellValue("STATUS");

		for(String[] data : datas) {
			XSSFRow row = sheet.createRow((short) cont++);
            row.createCell(0).setCellValue(data[2]);
            row.createCell(1).setCellValue(data[0]);
            row.createCell(2).setCellValue(data[1]);
            row.createCell(3).setCellValue("RUPTURA");
		}
		try {
			workbook.write(bos);
			workbook.close();
		}catch (Exception e) {
		}
		return bos.toByteArray();
	}
	
	public byte[] generateValidadeExcel(List<String[]> datas) {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int cont = 1;
		
		
		XSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("DATA");
		rowhead.createCell(1).setCellValue("LOJA");
		rowhead.createCell(2).setCellValue("PRODUTO");
		rowhead.createCell(3).setCellValue("VALIDADE");
		rowhead.createCell(4).setCellValue("ESTOQUE");
		
		for(String[] data : datas) {
			XSSFRow row = sheet.createRow((short) cont++);
            row.createCell(0).setCellValue(data[0]);
            row.createCell(1).setCellValue(data[4]);
            row.createCell(2).setCellValue(data[3]);
            row.createCell(3).setCellValue(data[5]);
            row.createCell(4).setCellValue(data[6]);
		}
		try {
			workbook.write(bos);
			workbook.close();
		}catch (Exception e) {
		}
		return bos.toByteArray();

	}
}
