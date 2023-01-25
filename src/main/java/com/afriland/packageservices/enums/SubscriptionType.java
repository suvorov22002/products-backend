package com.afriland.packageservices.enums;

import lombok.Getter;
import lombok.Setter;

public enum SubscriptionType {

    MAC_MTN("MAC_MTN"),
    MAC_ORANGE("MAC_ORANGE"),
    SMS_FIRST("SMS_FIRST");

    @Getter
    @Setter
    public String value;

    private SubscriptionType(String value) {

        this.setValue(value);
    }
}