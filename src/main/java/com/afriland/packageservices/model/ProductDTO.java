package com.afriland.packageservices.model;

import com.afriland.packageservices.entity.Subscriber;
import com.afriland.packageservices.enums.SubscriptionType;
import com.afriland.packageservices.utils.MACMtnUtils;
import com.afriland.packageservices.utils.MACOrangeUtils;
import com.afriland.packageservices.utils.SMSFirstUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID id;
    private String name;
    private String code;

    public String toString() {

        return this.name + " (" + this.code + ")";
    }

    public Map<String, Object> subscribe(Subscriber subscriber) {

        Map<String, Object> result = new HashMap<>();

        switch (SubscriptionType.valueOf(this.getCode())) {
            case MAC_MTN:
                result = MACMtnUtils.subscribe(this);

                break;

            case MAC_ORANGE:
                result = MACOrangeUtils.subscribe(this);
                break;

            case SMS_FIRST:
                result = SMSFirstUtils.subscribe(this);
                break;

            default:

                break;
        }

        return result;
    }
}
