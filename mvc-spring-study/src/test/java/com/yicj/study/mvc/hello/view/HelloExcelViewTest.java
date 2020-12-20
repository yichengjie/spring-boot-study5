package com.yicj.study.mvc.hello.view;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

@Slf4j
public class HelloExcelViewTest {

    @Test
    public void test1() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest() ;
        MockHttpServletResponse response = new MockHttpServletResponse() ;
        HelloExcelView view = new HelloExcelView() ;
        view.render(new HashMap<>(), request, response);
        // 从response中获取excel数据
        byte[] contentAsByteArray = response.getContentAsByteArray();
        XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(contentAsByteArray));
        XSSFSheet sheet = wb.getSheet("Test Sheet");
        log.info("sheetName : {}", sheet.getSheetName());
        XSSFRow row0 = sheet.getRow(0);
        Assert.assertEquals("test1",row0.getCell(0).getStringCellValue());
        Assert.assertEquals("test2",row0.getCell(1).getStringCellValue());
        Assert.assertEquals("test3",row0.getCell(2).getStringCellValue());
    }
}
