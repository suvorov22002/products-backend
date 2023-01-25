package com.afriland.packageservices.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum PhoneNumberPattern {

    MTN("MTN"),
    ORANGE("ORANGE"),

    NOT_CONSIDERED("NOT_CONSIDERED");

    @Getter
    @Setter
    public String value;

    private PhoneNumberPattern(String value) {

        this.setValue(value);
    }
}
