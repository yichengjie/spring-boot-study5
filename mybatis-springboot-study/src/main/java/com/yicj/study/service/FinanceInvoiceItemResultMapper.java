package com.yicj.study.service;

import com.yicj.study.common.ResultMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("financeInvoiceItemResultMapper")
public class FinanceInvoiceItemResultMapper implements ResultMapper {

    @Override
    public Object handler(ResultSet rs) throws SQLException {
        BaseExportList<BdInvoiceExport> list = new BaseExportList<>() ;
        int row = 1 ;
        while (rs != null && rs.next()){
            list.add(rs.getLong(1) +"", (BdInvoiceExport)handlerRow(rs, row)) ;
        }
        return list;
    }

    @Override
    public Object handlerRow(ResultSet rs, int rowNum) throws SQLException {
        BdInvoiceExport bdInvoiceExport = new BdInvoiceExport();
        bdInvoiceExport.setId(rs.getLong(1)) ;
        bdInvoiceExport.setName(rs.getString(2)) ;
        // ...
        return bdInvoiceExport;
    }
}
