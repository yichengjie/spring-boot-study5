package com.yicj.study.transaction.bootstrap.anno.component;

import com.yicj.study.transaction.service.IQuoteService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyComponent {

    @Resource(name = "quoteService")
    private IQuoteService quoteService ;
}
