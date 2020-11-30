package com.yicj.study.exception;

import com.yicj.study.constant.CommonConstants;

public class TokenException extends BaseException {
    public TokenException(String msg) {
        super(CommonConstants.EX_TOKEN_CODE, msg);
    }
}
