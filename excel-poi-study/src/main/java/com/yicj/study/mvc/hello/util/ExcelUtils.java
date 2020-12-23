package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.annotation.ExcelProperty;
import com.yicj.study.mvc.hello.common.ColumnInfo;
import com.yicj.study.mvc.hello.common.ExportSheetInfo;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    public static Workbook exportExcel(ExportSheetInfo exportExcelInfo) {
        List<ExportSheetInfo> list = new ArrayList<>();
        return exportExcel(list);
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

    //https://blog.csdn.net/noaman_wgs/article/details/85878331
    static class ExcelSheetImportHelper {
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");
        private static final DecimalFormat df = new DecimalFormat("#");

        /**
         * 读取Excel表格表头的内容
         * @param workbook
         * @return String 表头内容的数组
         */
        public static List<String> readExcelTitle(Workbook workbook, String fileName) throws Exception {
            List<String> titles = Lists.newArrayList();
            if (workbook == null) {
                return titles;
            }
            Sheet sheet = workbook.getSheetAt(0);
            //excel为空
            if (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0) {
                return titles;
            }
            //得到首行的row
            Row row = sheet.getRow(0);
            //标题总列数
            int colNum = row.getPhysicalNumberOfCells();
            for (int i = 0; i < colNum; i++) {
                titles.add(getStringCellValue(row.getCell(i)));
            }
            return titles;
        }

        /**
         * 读取excel文件内容
         * @param workbook 文件输入流, excel具体内容
         * @param fileName 文件名,通过文件后缀名判断excel版本
         * @return
         * @throws Exception
         */
        public static List<List<String>> readExcelContent(Workbook workbook, String fileName) throws Exception {
            List<List<String>> result = Lists.newArrayList();
            if (workbook == null) {
                return result;
            }
            try {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(0);
                if (row == null) {
                    return result;
                }
                int colNum = row.getLastCellNum();
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 0; i <= rowNum; i++) {
                    row = sheet.getRow(i);
                    // 过滤空白行
                    if (isBlankRow(colNum, row)) {
                        continue;
                    }
                    List<String> rowContents = Lists.newArrayList();
                    for (int j = 0; j < colNum; j++) {
                        rowContents.add(getCellValue(row.getCell(j)));
                    }
                    result.add(rowContents);
                }
            } catch (Exception e) {
                throw new Exception("excel解析失败！");
            }

            return result;
        }

        private static boolean isBlankRow(int colNum, Row row) {
            for (int i = 0; i <= colNum; i++) {
                // 只要有一列不为空, 该行就不为空行
                if (StringUtils.isNotBlank(getCellValue(row.getCell(i)))) {
                    return false;
                }
            }
            return true;
        }
        /**
         * 获取单元格数据内容为字符串类型的数据
         *
         * @param cell Excel单元格
         * @return String 单元格数据内容
         */
        private static String getStringCellValue(Cell cell) {
            String cellValue = StringUtils.EMPTY;
            if (cell != null) {
                cellValue = cell.getStringCellValue().trim();
            }
            return cellValue;
        }

        /**
         * 对表格中数值进行格式化
         *
         * @param cell Excel单元格
         * @return
         */
        public static String getCellValue(Cell cell) {
            String value = StringUtils.EMPTY;
            if (cell != null) {
                switch (cell.getCellType()) {
                    case CellType.STRING:
                        value = cell.getRichStringCellValue().getString().trim();
                        break;
                    case CellType.NUMERIC:
                        if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                            value = sdf.format(cell.getDateCellValue());
                        } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            value = sdf.format(date);
                        } else {
                            value = df.format(cell.getNumericCellValue());
                        }
                        break;
                    case CellType.BOOLEAN:
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case CellType.BLANK:
                        value = "" ;
                        break;
                    default:
                        break;
                }
            }
            return value;
        }
    }


    @Data
    static class ExcelSheetExportHelper {
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
                this.columnInfos = this.parseColumnInfos(clazz);
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

        private List<ColumnInfo> parseColumnInfos(Class<?> clazz) {
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
                    columnInfo.setColumnType(this.obtainColumnType(field, anno));
                    retList.add(columnInfo);
                }
            }
            return retList;
        }

        private Class<?> obtainColumnType(Field field, ExcelProperty anno) {
            if (anno.columnType() != String.class) {
                return anno.columnType();
            }
            return field.getType();
        }
    }


}
