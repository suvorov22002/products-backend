package com.afriland.packageservices.service;

import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Subscriber> findAll() {

        return subscriberRepository.findAll();
    }

    public Subscriber create(Subscriber subscriber) {

        return subscriberRepository.save(subscriber);
    }

    public List<Subscriber> findByCode(String clientCode) {

        return subscriberRepository.findByCode(clientCode);
    }

}
