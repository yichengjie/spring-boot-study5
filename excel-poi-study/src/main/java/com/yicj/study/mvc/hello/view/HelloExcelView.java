package com.yicj.study.mvc.hello.view;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Sheet sheet = workbook.createSheet("Test Sheet");
        // 在excel表格中加入测试数据
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("1");
        row.createCell(0).setCellValue("test1");
        row.createCell(1).setCellValue("test2");
        row.createCell(2).setCellValue("test3");
    }
}
