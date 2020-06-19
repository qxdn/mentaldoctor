package com.mentaldoctor.mentaldoctor.handler;

import com.mentaldoctor.mentaldoctor.exception.OrderInvalidException;
import com.mentaldoctor.mentaldoctor.model.dto.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderInvalidExceptionHandler {

    @ExceptionHandler(OrderInvalidException.class)
    public RespBean orderInvalidException(OrderInvalidException exception){
        return RespBean.error(null,exception);
    }

}
