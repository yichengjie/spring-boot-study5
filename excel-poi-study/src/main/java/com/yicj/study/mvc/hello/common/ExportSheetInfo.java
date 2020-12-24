package com.yicj.study.mvc.hello.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ExportSheetInfo {
    private String sheetName;
    private List<?> dataList;
    private List<ColumnInfo> columnInfos;
}