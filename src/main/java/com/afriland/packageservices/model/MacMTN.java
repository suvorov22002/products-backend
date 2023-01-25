package com.afriland.packageservices.model;

import com.afriland.packageservices.entity.Subscriber;

import java.util.Map;

public class MacMTN extends ProductDTO {

    @Override
    public Map<String, Object> subscribe(Subscriber subscriber) {

        System.out.println("JE SUIS LA SOUSCRIPTION MAC MTN");



        return null;
    }
}
