package com.elosinfo.payments.handler;

import com.elosinfo.payments.exception.PaymentException;
import com.elosinfo.payments.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //interceptor de exceções
public class ResourceHandler {

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidOperationException(PaymentException paymentException){
        ErrorResponse.ErrorResponseBuilder error = ErrorResponse.builder();
        error.httpStatus(paymentException.getHttpStatus().value());
        error.message(paymentException.getMessage());
        error.timestamp(System.currentTimeMillis());
        error.xtid(paymentException.getXtid());
        return ResponseEntity.status(paymentException.getHttpStatus()).body(error.build());
    }
}
