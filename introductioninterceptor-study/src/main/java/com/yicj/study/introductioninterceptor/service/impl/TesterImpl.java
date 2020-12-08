package com.yicj.study.introductioninterceptor.service.impl;

import com.yicj.study.introductioninterceptor.service.ITester;

public class TesterImpl implements ITester {

    private boolean busyAsTester ;

    @Override
    public boolean isBusyAsTester() {
        return busyAsTester;
    }

    @Override
    public void testSoftware() {
        System.out.println("I will ensure the quality .");
    }

    public void setBusyAsTester(boolean busyAsTester) {
        this.busyAsTester = busyAsTester;
    }
}
