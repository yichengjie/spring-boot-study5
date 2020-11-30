package com.yicj.study.exception;

import com.yicj.study.constant.CommonConstants;

public class DuplicateKeyException extends BaseException {

    public DuplicateKeyException(String msg) {
        super(CommonConstants.EX_DUPLICATE_KEY_CODE,msg);
    }
}
