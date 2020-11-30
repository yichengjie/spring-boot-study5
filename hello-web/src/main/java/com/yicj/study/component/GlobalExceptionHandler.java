package com.yicj.study.component;

import com.yicj.study.constant.CommonConstants;
import com.yicj.study.exception.BaseException;
import com.yicj.study.exception.DuplicateKeyException;
import com.yicj.study.exception.TokenException;
import com.yicj.study.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(BaseException ex){
        log.error("global base exception [{}] ", CommonConstants.EX_BASE_CODE, ex);
        return new BaseResponse(ex.getCode(), ex.getMessage()) ;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(Exception ex){
        log.error("global other exception [{}] ", CommonConstants.EX_OTHER_CODE, ex);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, ex.getMessage()) ;
    }

    @ExceptionHandler(TokenException.class)
    public BaseResponse tokenExceptionHandler(Exception ex){
        log.error("global token exception [{}] ", CommonConstants.EX_TOKEN_CODE, ex);
        return new BaseResponse(CommonConstants.EX_TOKEN_CODE, ex.getMessage()) ;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResponse duplicateKeyExceptionHandler(DuplicateKeyException ex){
        log.error("global token exception [{}] ", CommonConstants.EX_DUPLICATE_KEY_CODE, ex);
        return new BaseResponse(CommonConstants.EX_DUPLICATE_KEY_CODE, ex.getMessage()) ;
    }

}
