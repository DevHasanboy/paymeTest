package com.example.paymeetest.request;

import com.example.paymeetest.entity.Account;
import lombok.Data;

@Data
public class PaymeParams {
    private String id;
    private Long amount;
    private Account account;
}

