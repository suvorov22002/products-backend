package com.afriland.packageservices.service;


import com.afriland.packageservices.entity.CBSClient;
import com.afriland.packageservices.entity.Pack;
import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.repository.CBSClientRepository;
import com.afriland.packageservices.repository.PackRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CBSClientService {

    @Autowired
    private CBSClientRepository cbsClientRepository;


    public List<CBSClient> findAll() {

        return cbsClientRepository.findAll();
    }

    public CBSClient create(CBSClient cbsClient) {

        return cbsClientRepository.save(cbsClient);
    }

    public List<CBSClient> findByCode(String code) {

        return cbsClientRepository.findByCode(code);
    }
}
