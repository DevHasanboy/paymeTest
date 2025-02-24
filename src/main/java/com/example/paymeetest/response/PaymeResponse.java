package com.example.paymeetest.response;

import com.example.paymeetest.dto.PaymeError;
import lombok.Data;

@Data
public class PaymeResponse {
    private Object result;
    private PaymeError error;
}

