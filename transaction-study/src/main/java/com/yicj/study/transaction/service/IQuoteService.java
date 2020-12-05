package com.yicj.study.transaction.service;

import com.yicj.study.transaction.entity.Quote;
import org.joda.time.DateTime;

public interface IQuoteService {
    Quote getQuote() ;
    Quote getQuoteByDateTime(DateTime dateTime) ;
    void saveQuote(Quote quote) ;
    void updateQuote(Quote quote) ;
    void deleteQuote(Quote quote) ;
}
