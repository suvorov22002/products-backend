package com.afriland.packageservices.enums;

import lombok.Getter;
import lombok.Setter;

public enum SubscriptionStatus {

    ACTIVATE("ACTIVATE"),

    ACTIVATE_CBS("ACTIVATE CBS"),

    WAITING("WAITING");

    @Getter
    @Setter
    public String value;

    private SubscriptionStatus(String value) {

        this.setValue(value);
    }


}
