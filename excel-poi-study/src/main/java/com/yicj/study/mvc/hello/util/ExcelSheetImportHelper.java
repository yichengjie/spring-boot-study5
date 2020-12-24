package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.common.ColumnInfo;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.ArrayList;
import java.util.List;

public class ExcelSheetImportHelper<T> {
    private Class<T> clazz ;
    private List<ColumnInfo> columnInfos ;
    private Sheet sheet ;

    public ExcelSheetImportHelper(Class<T> clazz, Sheet sheet){
        this.clazz = clazz ;
        columnInfos = new ArrayList<>() ;
        this.sheet = sheet ;
        this.readExcelTitle();
    }

    /**
     * 读取Excel表格表头的内容
     * @return String 表头内容的数组
     */
    private void readExcelTitle(){
        //得到首行的row
        Row row = sheet.getRow(0);
        List<ColumnInfo> tempColumnInfos = ExcelUtils.parseColumnInfos(clazz);
        //标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colNum; i++) {
            String titleName = getStringCellValue(row.getCell(i));
            ColumnInfo columnInfo = this.matchColumnInfo(tempColumnInfos, titleName);
            columnInfos.add(columnInfo) ;
        }
    }

    public List<T> readExcelContent() {
        List<T> result = Lists.newArrayList();
        Row row = sheet.getRow(0);
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
            T t = this.getInstance(clazz) ;
            BeanWrapper wrapper = new BeanWrapperImpl(t) ;
            for (int j = 0; j < colNum; j++) {
                String cellValue = getCellValue(row.getCell(j));
                ColumnInfo columnInfo = columnInfos.get(j);
                wrapper.setPropertyValue(columnInfo.getFieldName(), cellValue);
            }
            result.add(t);
        }
        return result;
    }

    private T getInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }

    private ColumnInfo matchColumnInfo(List<ColumnInfo> tempColumnInfos, String titleName){
        for (ColumnInfo columnInfo: tempColumnInfos){
            if (titleName.equalsIgnoreCase(columnInfo.getTitle())){
                return columnInfo ;
            }
        }
        return null ;
    }

    private boolean isBlankRow(int colNum, Row row) {
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
    private String getStringCellValue(Cell cell) {
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
    public String getCellValue(Cell cell) {
        String value = StringUtils.EMPTY;
        if (cell != null) {
            CellType cellType = cell.getCellType();
            switch (cellType) {
                case NUMERIC:
                    value = String.valueOf(cell.getStringCellValue());
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    value = cell.getStringCellValue();
                    break;
            }
        }
        return value;
    }
}