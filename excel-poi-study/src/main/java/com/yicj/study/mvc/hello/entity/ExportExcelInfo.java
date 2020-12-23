package com.yicj.study.mvc.hello.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ExportExcelInfo {
    private String sheetName;
    private List<?> dataList;
    private List<ColumnInfo> columnInfos;
}