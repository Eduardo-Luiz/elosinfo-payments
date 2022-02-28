package com.elosinfo.payments.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Rejection {
    private String code;
    private String field;
    private String message;
}
