package com.elosinfo.payments.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class PaymentException extends RuntimeException {

    private static final long serialVersionUID = 8042402636193525393L;
    private final HttpStatus httpStatus;
    private final String xtid;

    public PaymentException(final String message, final HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
        this.xtid = UUID.randomUUID().toString();
    }

}
