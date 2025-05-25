package com.ultra.rmq.exception;

import lombok.Data;

@Data
public class ApiException {
    private int status;
    private String message;
    private String timestamp;
}
