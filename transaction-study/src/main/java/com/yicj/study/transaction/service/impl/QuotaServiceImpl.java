package com.yicj.study.transaction.service.impl;

import com.yicj.study.transaction.entity.Quota;
import com.yicj.study.transaction.service.IQuotaService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuotaServiceImpl implements IQuotaService {

    private final JdbcTemplate jdbcTemplate ;

    @Override
    @Transactional
    public Quota getQuota() {
        String sql = "select * from quota where id =1" ;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Quota quota = new Quota() ;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            quota.setId(id);
            quota.setName(name);
            return quota ;
        }) ;
    }

    @Override
    public Quota getQuotaByDateTime(DateTime dateTime) {
        return null;
    }

    @Override
    public void saveQuota(Quota quota) {

    }

    @Override
    public void updateQuota(Quota quota) {

    }

    @Override
    public void deleteQuota(Quota quota) {

    }
}
