package com.elosinfo.payments.handler;

import com.elosinfo.payments.exception.PaymentException;
import com.elosinfo.payments.model.Rejection;
import com.elosinfo.payments.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice //interceptor de exceções
public class ResourceHandler {

    private final String DATA_ENTRY_VALIDATION = "DATA_ENTRY_VALIDATION";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response>
    handlerMethodArgumentNotValidException(MethodArgumentNotValidException m){

        List<Rejection> rejections = new ArrayList<>();

        m.getBindingResult().getAllErrors().forEach((erro) -> {
            String field = ((FieldError)erro).getField();
            String message = erro.getDefaultMessage();

            Rejection.RejectionBuilder rejection = Rejection.builder()
                    .code(DATA_ENTRY_VALIDATION)
                    .field(field)
                    .message(message);

            rejections.add(rejection.build());

        });

        Response.ResponseBuilder errorResponse = Response.builder();
        errorResponse
                .rejections(rejections)
                .xtid(this.getXtidValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse.build());
    }

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity<Response> handlerInvalidOperationException(PaymentException paymentException){

        Rejection.RejectionBuilder rejection = Rejection.builder()
                .code(paymentException.getCode())
                .message(paymentException.getMessage());

        List<Rejection> rejections = new ArrayList<>();
        rejections.add(rejection.build());

        Response.ResponseBuilder error = Response.builder()
                .xtid(this.getXtidValue())
                .rejections(rejections);

        error.xtid(this.getXtidValue());

        return ResponseEntity.status(paymentException.getHttpStatus()).body(error.build());
    }

    private String getXtidValue(){
        return UUID.randomUUID().toString();
    }
}
