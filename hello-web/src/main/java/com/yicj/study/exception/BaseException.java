package com.yicj.study.exception;

import com.yicj.study.constant.CommonConstants;
import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    private String code ;

    public BaseException(String msg){
        super(msg);
        this.code = CommonConstants.EX_BASE_CODE ;
    }

    public BaseException(String code, String msg){
        super(msg);
        this.code = code ;
    }
}
