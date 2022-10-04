package com.kai.zooapiproducer.vo;

import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
}
