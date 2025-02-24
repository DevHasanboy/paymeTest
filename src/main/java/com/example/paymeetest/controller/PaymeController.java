package com.example.paymeetest.controller;

import com.example.paymeetest.request.PaymeRequest;
import com.example.paymeetest.response.PaymeResponse;
import com.example.paymeetest.service.PaymeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payme")
public class PaymeController {

    private final PaymeService paymeService;

    public PaymeController(PaymeService paymeService) {
        this.paymeService = paymeService;
    }

    @PostMapping("/process")
    public PaymeResponse handlePaymeRequest(@RequestBody PaymeRequest request) {
        return paymeService.processRequest(request);
    }
}

