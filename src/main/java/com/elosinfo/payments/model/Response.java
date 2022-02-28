package com.elosinfo.payments.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Response {

    private long timestamp;
    private String xtid;
    private List<Rejection> rejections;

}
