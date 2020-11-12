package com.stock.portfoliomanager.controller;

import com.stock.portfoliomanager.api.TransactionApi;
import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionApi transactionApi;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionEntity>> createTransaction(@Validated @RequestBody Transaction transaction) {
        List<TransactionEntity> entities = transactionApi.save(transaction);
        return ResponseEntity.ok(entities);
    }
}
