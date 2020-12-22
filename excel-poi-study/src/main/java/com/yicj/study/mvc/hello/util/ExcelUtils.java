package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.annotation.ExcelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    @Data
    @AllArgsConstructor
    private static class ColumnInfo{
        private String fieldName ;
        private String title ;
        private int index ;
        private boolean adaptive   ;
    }

    @Data
    @AllArgsConstructor
    public static class ExportExcelInfo{
        private String sheetName ;
        private List<?> dataList ;
        private List<ColumnInfo> columnInfos ;
    }

    public static Workbook exportExcel(ExportExcelInfo exportExcelInfo) {
        List<ExportExcelInfo> list = new ArrayList<>() ;
        return exportExcel(list) ;
    }

    public static Workbook exportExcel(List<ExportExcelInfo> list) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        for (ExportExcelInfo exportExcelInfo: list){
            if (!CollectionUtils.isEmpty(exportExcelInfo.getDataList())){
                doExportExcel(hssfWorkbook, exportExcelInfo);
            }
        }
        return hssfWorkbook;
    }

    private static void doExportExcel(HSSFWorkbook hssfWorkbook, ExportExcelInfo exportExcelInfo){
        //将key值设为sheet名称
        HSSFSheet sheet = hssfWorkbook.createSheet(exportExcelInfo.getSheetName());
        //1. 整理表头信息
        List<ColumnInfo> columnInfos = exportExcelInfo.getColumnInfos() ;
        if (CollectionUtils.isEmpty(columnInfos)){
            columnInfos = parseColumnInfos(exportExcelInfo.getDataList());
        }
        // 2. 填充表头数据
        populateTitleData(sheet, columnInfos);
        //3. 填充表格数据
        for (int i = 0; i < exportExcelInfo.getDataList().size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object o = exportExcelInfo.getDataList().get(i);
            populateRowData(row, o, columnInfos);
        }
    }

    private static void populateTitleData(HSSFSheet sheet, List<ColumnInfo> columnInfos){
        Row titleRow = sheet.createRow(0);
        for (ColumnInfo columnInfo: columnInfos){
            Cell cell = titleRow.createCell(columnInfo.getIndex());
            cell.setCellValue(columnInfo.getTitle());
            if (columnInfo.isAdaptive()){
                sheet.autoSizeColumn(columnInfo.index);
            }
        }
    }

    private static void populateRowData(Row row, Object o, List<ColumnInfo> columnInfos){
        BeanWrapper instance = new BeanWrapperImpl(o) ;
        for (ColumnInfo columnInfo: columnInfos){
            String fieldName = columnInfo.getFieldName();
            Object value = instance.getPropertyValue(fieldName);
            Cell cell = row.createCell(columnInfo.getIndex());
            cell.setCellValue(value == null ? "" : String.valueOf(value));
        }
    }


    private static List<ColumnInfo> parseColumnInfos(List<?> list){
        //使用反射抓取list的泛型
        Type type = list.getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) type;
        Class cls = (Class) p.getActualTypeArguments()[0];
        //可能有即便list可能传递为空，也需要导出到表格的情况发生，所以弃置定位取类型的方法
        //Field[] fields = list.get(0).getClass().getDeclaredFields();
        Field[] fields = cls.getDeclaredFields();
        List<ColumnInfo> retList = new ArrayList<>() ;
        for (int i = 0, m = fields.length; i < m; i++) {
            //判断该属性是否是需要导出的列，不是则直接跳过
            if (!fields[i].isAnnotationPresent(ExcelAttribute.class)) {
                continue;
            }
            //获取属性的对应注解
            ExcelAttribute anno = fields[i].getAnnotation(ExcelAttribute.class);
            if (anno.isExport()){
                String title = anno.name();
                int index = anno.column() - 1;
                boolean adaptive = anno.isAdaptive() ;
                retList.add(new ColumnInfo(fields[i].getName(),title, index, adaptive)) ;
            }
        }
        return retList ;
    }

}
