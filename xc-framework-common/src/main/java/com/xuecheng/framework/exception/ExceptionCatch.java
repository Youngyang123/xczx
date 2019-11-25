package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionCatch {

    // 可预知异常
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException) {
        log.error("catch exception : {}\r\nexception: ", customException.getMessage(), customException);
        return new ResponseResult(customException.getResultCode());
    }
}
