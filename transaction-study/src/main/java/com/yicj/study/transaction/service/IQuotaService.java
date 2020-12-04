package com.yicj.study.transaction.service;

import com.yicj.study.transaction.entity.Quota;
import org.joda.time.DateTime;

public interface IQuotaService {
    Quota getQuota() ;
    Quota getQuotaByDateTime(DateTime dateTime) ;
    void saveQuota(Quota quota) ;
    void updateQuota(Quota quota) ;
    void deleteQuota(Quota quota) ;
}
