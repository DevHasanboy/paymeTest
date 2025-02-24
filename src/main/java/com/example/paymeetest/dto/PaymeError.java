package com.example.paymeetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymeError {
    private int code;
    private String field;
}
