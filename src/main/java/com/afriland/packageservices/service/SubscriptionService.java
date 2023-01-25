package com.afriland.packageservices.service;

import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.entity.Subscription;
import com.afriland.packageservices.repository.SubscriberRepository;
import com.afriland.packageservices.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {

        return subscriptionRepository.findAll();
    }

}
