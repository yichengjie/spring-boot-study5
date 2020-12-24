package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.annotation.ExcelProperty;
import com.yicj.study.mvc.hello.common.ColumnInfo;
import com.yicj.study.mvc.hello.common.ExportSheetInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {


    public static <T>List<T> importExcel(Class<T> clazz, Sheet sheet){
        ExcelSheetImportHelper<T> excelSheetImportHelper = new ExcelSheetImportHelper<>(clazz, sheet);
        return excelSheetImportHelper.readExcelContent() ;
    }


    public static Workbook exportExcel(List<ExportSheetInfo> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExportSheetInfo exportExcelInfo : list) {
            if (!CollectionUtils.isEmpty(exportExcelInfo.getDataList())) {
                new ExcelSheetExportHelper(workbook, exportExcelInfo).doExportExcel();
            }
        }
        return workbook;
    }

    public static List<ColumnInfo> parseColumnInfos(Class<?> clazz) {
        //可能有即便list可能传递为空，也需要导出到表格的情况发生，所以弃置定位取类型的方法
        //Field[] fields = list.get(0).getClass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        List<ColumnInfo> retList = new ArrayList<>();
        for (int i = 0, m = fields.length; i < m; i++) {
            //判断该属性是否是需要导出的列，不是则直接跳过
            if (!fields[i].isAnnotationPresent(ExcelProperty.class)) {
                continue;
            }
            Field field = fields[i];
            //获取属性的对应注解
            ExcelProperty anno = field.getAnnotation(ExcelProperty.class);
            if (anno.isExport()) {
                String title = anno.name();
                int index = anno.columnNumber() - 1;
                boolean adaptive = anno.isAdaptive();
                ColumnInfo columnInfo = new ColumnInfo(field.getName(), title, index, adaptive);
                columnInfo.setColumnType(obtainColumnType(field, anno));
                retList.add(columnInfo);
            }
        }
        return retList;
    }

    private static Class<?> obtainColumnType(Field field, ExcelProperty anno) {
        if (anno.columnType() != String.class) {
            return anno.columnType();
        }
        return field.getType();
    }
}
