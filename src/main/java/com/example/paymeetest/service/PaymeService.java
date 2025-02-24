package com.example.paymeetest.service;

import com.example.paymeetest.dto.PaymeError;
import com.example.paymeetest.request.PaymeParams;
import com.example.paymeetest.request.PaymeRequest;
import com.example.paymeetest.response.PaymeResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymeService {
    private final Map<String, String> transactions = new HashMap<>();

    public PaymeResponse processRequest(PaymeRequest request) {
        PaymeResponse response = new PaymeResponse();

        switch (request.getMethod()) {
            case "CheckPerformTransaction":
                response.setResult(checkPerformTransaction(request.getParams()));
                break;
            case "CreateTransaction":
                response.setResult(createTransaction(request.getParams()));
                break;
            case "PerformTransaction":
                response.setResult(performTransaction(request.getParams()));
                break;
            case "CancelTransaction":
                response.setResult(cancelTransaction(request.getParams()));
                break;
            case "CheckTransaction":
                response.setResult(checkTransaction(request.getParams()));
                break;
            default:
                response.setError(new PaymeError(-32601, "Method not found"));
        }

        return response;
    }

    private boolean checkPerformTransaction(PaymeParams params) {
        return params.getAmount() > 0; // Validate amount
    }

    private String createTransaction(PaymeParams params) {
        String transactionId = params.getId();
        transactions.put(transactionId, "created");
        return transactionId;
    }

    private String performTransaction(PaymeParams params) {
        String transactionId = params.getId();
        if (transactions.containsKey(transactionId)) {
            transactions.put(transactionId, "completed");
            return "Transaction " + transactionId + " completed";
        }
        return "Transaction not found";
    }

    private String cancelTransaction(PaymeParams params) {
        String transactionId = params.getId();
        if (transactions.containsKey(transactionId)) {
            transactions.put(transactionId, "canceled");
            return "Transaction " + transactionId + " canceled";
        }
        return "Transaction not found";
    }

    private String checkTransaction(PaymeParams params) {
        String transaction = transactions.get(params.getId());

        if (transaction == null) {
            return "not found";
        }

        return transaction;
    }

}

