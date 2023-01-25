package com.afriland.packageservices.controller;

import com.afriland.packageservices.dto.CBSClientDTO;
import com.afriland.packageservices.entity.CBSClient;
import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.repository.SubscriberRepository;
import com.afriland.packageservices.utils.CBSUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/cbs")
public class CBSController {

    private SubscriberRepository subscriberRepository;

    @Value("${cbs.service.url}")
    private String cbsBaseURL;

    @Value("${cbs.service.find-customer}")
    private String cbsFindCustomerService;

    @RequestMapping(method = RequestMethod.GET, value = "/find-subscriber")
    public ResponseEntity<Object> findSubscriber(@RequestParam("clientCode") String clientCode) {

        if (clientCode.length() != 7) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", HTTPResponseMessage.RESOURCE_NOT_EXISTS);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }

        CBSClientDTO cbsClientDTO = CBSUtils.getClient(clientCode);

        return ResponseEntity.ok(cbsClientDTO);
    }
}
