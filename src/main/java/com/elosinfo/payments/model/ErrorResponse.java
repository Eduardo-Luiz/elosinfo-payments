package com.elosinfo.payments.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private String message;
    private int httpStatus;
    private long timestamp;
    private String xtid;
}
