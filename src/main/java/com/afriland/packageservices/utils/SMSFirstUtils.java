package com.afriland.packageservices.utils;

import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.model.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class SMSFirstUtils {

    public static Map<String, Object> subscribe(ProductDTO productDTO) {

        Map<String, Object> result = new HashMap<>();

        result.put(HTTPResponseMessage.SUBSCRIBED.getValue(), true);
        result.put(HTTPResponseMessage.MESSAGE.getValue(), HTTPResponseMessage.SUCCESSFULLY_SUBSCRIBED);

        System.out.println(productDTO.getCode());

        return result;
    }
}
