package com.yicj.study.introductioninterceptor.service.impl;

import com.yicj.study.introductioninterceptor.service.IDeveloper;

public class DeveloperImpl implements IDeveloper {
    @Override
    public void developSoftware() {
        System.out.println("I am happy with programming.");
    }
}
