package com.backend.ex;

import com.backend.bean.ResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResultBean<Void> handleGlobalException(GlobalException e) {
        return ResultBean.error(e.getCode(), e.getMsg(), null);
    }

}
