package com.afriland.packageservices.enums;

import lombok.Getter;
import lombok.Setter;

public enum MaxCodeLength {

    PRODUCT(10),

    PACKAGE(20);


    @Getter
    @Setter
    public int value;

    private MaxCodeLength(int value) {

        this.setValue(value);
    }

}
