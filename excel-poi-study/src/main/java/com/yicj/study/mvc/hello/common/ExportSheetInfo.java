package com.yicj.study.mvc.hello.common;

import com.yicj.study.mvc.hello.common.ColumnInfo;
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