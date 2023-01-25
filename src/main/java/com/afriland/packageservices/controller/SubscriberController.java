package com.afriland.packageservices.controller;

import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.model.SubscriberDTO;
import com.afriland.packageservices.requirement.IModelController;
import com.afriland.packageservices.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Subscriber>> all() {

        return ResponseEntity.ok(subscriberService.findAll());
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<SubscriberDTO> update() {
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public ResponseEntity<Iterable<SubscriberDTO>> find() {
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseEntity<SubscriberDTO> delete() {
        return null;
    }
}
