package com.yicj.study.transaction.service.impl;

import com.yicj.study.transaction.entity.Quote;
import com.yicj.study.transaction.service.IQuoteService;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class QuoteServiceImpl implements IQuoteService {
    private final JdbcTemplate jdbcTemplate ;
    public QuoteServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate ;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
    @Override
    public Quote queryById() {
        String sql = "select * from quota where id =1" ;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Quote quota = new Quote() ;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            quota.setId(id);
            quota.setName(name);
            return quota ;
        }) ;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
    @Override
    public Quote getQuoteByDateTime(DateTime dateTime) {
        throw new NotImplementedException() ;
    }

    @Override
    public void saveQuote(Quote quota) {
        throw new NotImplementedException() ;
    }

    @Override
    public void updateQuote(Quote quota) {
        throw new NotImplementedException() ;
    }

    @Override
    public void deleteQuote(Quote quota) {
        throw new NotImplementedException() ;
    }
}
