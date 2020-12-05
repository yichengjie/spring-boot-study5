package com.yicj.study.transaction.service.impl;

import com.yicj.study.transaction.entity.Quote;
import com.yicj.study.transaction.service.IQuoteService;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuoteServiceImpl implements IQuoteService {
    private final JdbcTemplate jdbcTemplate ;
    public QuoteServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate ;
    }

    @Override
    public Quote getQuote() {
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

    @Override
    public Quote getQuoteByDateTime(DateTime dateTime) {
        return null;
    }

    @Override
    public void saveQuote(Quote quota) {

    }

    @Override
    public void updateQuote(Quote quota) {

    }

    @Override
    public void deleteQuote(Quote quota) {

    }
}
