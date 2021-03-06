package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.common.ColumnInfo;
import com.yicj.study.mvc.hello.common.ExportSheetInfo;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.NumberUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Data
public class ExcelSheetExportHelper {
    private Workbook workbook;
    private Sheet sheet;
    private List<?> dataList;
    private List<ColumnInfo> columnInfos;

    public ExcelSheetExportHelper(Workbook workbook, ExportSheetInfo sheetInfo) {
        this.workbook = workbook;
        this.sheet = workbook.createSheet(sheetInfo.getSheetName());
        //使用反射抓取list的泛型
        this.dataList = sheetInfo.getDataList();
        Type type = dataList.getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) type;
        Class clazz = (Class) p.getActualTypeArguments()[0];
        this.columnInfos = sheetInfo.getColumnInfos();
        if (CollectionUtils.isEmpty(this.columnInfos)) {
            this.columnInfos = ExcelUtils.parseColumnInfos(clazz);
        }
    }

    public void doExportExcel() {
        // 2. 填充表头数据
        populateTitleData(sheet, columnInfos);
        //3. 填充表格数据
        for (int i = 0; i < this.getDataList().size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object o = this.getDataList().get(i);
            populateRowData(row, o, columnInfos);
        }
    }

    private void populateTitleData(Sheet sheet, List<ColumnInfo> columnInfos) {
        Row titleRow = sheet.createRow(0);
        for (ColumnInfo columnInfo : columnInfos) {
            Cell cell = titleRow.createCell(columnInfo.getIndex());
            cell.setCellValue(columnInfo.getTitle());
            if (columnInfo.isAdaptive()) {
                sheet.autoSizeColumn(columnInfo.getIndex());
            }
        }
    }

    private void populateRowData(Row row, Object o, List<ColumnInfo> columnInfos) {
        BeanWrapper instance = new BeanWrapperImpl(o);
        for (ColumnInfo columnInfo : columnInfos) {
            String fieldName = columnInfo.getFieldName();
            Object value = instance.getPropertyValue(fieldName);
            Cell cell = row.createCell(columnInfo.getIndex());
            String columnContent = value == null ? "" : String.valueOf(value);
            writeCellValue(cell, columnContent, columnInfo.getColumnType());
        }
    }

    private void writeCellValue(Cell cell, String value, Class fieldType) {
        try {
            if (Integer.class == fieldType || java.lang.Long.class == fieldType) {
                double d = NumberUtils.parseNumber(value, Double.class);
                cell.setCellValue(d);
            } else if (java.lang.Double.class == fieldType || java.lang.Float.class == fieldType) {
                double d = NumberUtils.parseNumber(value, Double.class);
                cell.setCellValue(d);
            } else if (java.util.Date.class == fieldType) {
                CellStyle cellStyle = workbook.createCellStyle();
                short format = workbook.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
                cellStyle.setDataFormat(format);
                Date date = DateUtils.parseDate(value, "yyyy-mm-dd HH:mm");
                cell.setCellValue(date);
                cell.setCellStyle(cellStyle);
            } else if (java.lang.String.class == fieldType) {
                cell.setCellValue(value);
            } else if (java.lang.Boolean.class == fieldType) {
                cell.setCellValue(Boolean.valueOf(value));
            } else {
                cell.setCellValue("");
            }
        } catch (Exception e) {
            throw new RuntimeException("invalid cell value format");
        }
    }

}