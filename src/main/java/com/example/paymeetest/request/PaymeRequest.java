package com.example.paymeetest.request;

import lombok.Data;

@Data
public class PaymeRequest {
    private String method;
    private PaymeParams params;
}

