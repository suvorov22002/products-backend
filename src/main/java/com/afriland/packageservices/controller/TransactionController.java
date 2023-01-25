package com.afriland.packageservices.controller;

import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.entity.Transaction;
import com.afriland.packageservices.requirement.IModelController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController implements IModelController {

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Transaction> create() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<Transaction> update() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public ResponseEntity<Iterable<Transaction>> find() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseEntity<Transaction> delete() {
        return null;
    }
}
