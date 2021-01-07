package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常兜底
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseResult<String> genericExceptionHandler(Exception e){
        return ResponseResult.failedResult(CommonResultCode.FAILED, e.getLocalizedMessage());
    }
}
