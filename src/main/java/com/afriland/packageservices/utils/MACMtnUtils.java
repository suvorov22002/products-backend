package com.afriland.packageservices.utils;

import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.enums.PhoneNumberPattern;
import com.afriland.packageservices.model.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class MACMtnUtils {

    public static Map<String, Object> subscribe(ProductDTO productDTO) {

        Map<String, Object> result = new HashMap<>();

        result.put(HTTPResponseMessage.SUBSCRIBED.getValue(), true);
        result.put(HTTPResponseMessage.MESSAGE.getValue(), HTTPResponseMessage.SUCCESSFULLY_SUBSCRIBED);

        System.out.println(productDTO.getCode());

        return result;
    }

    public static Map<String, Object> bindAccountAndPhoneNumber(String accountNumber, String phoneNumber) {

        Map<String, Object> result = new HashMap<>();

        if (!ApplicationUtils.isValidPhoneNumber(phoneNumber, PhoneNumberPattern.MTN.value)) {

            result.put(HTTPResponseMessage.MESSAGE.getValue(), HTTPResponseMessage.BAD_PHONE_NUMBER_FORMAT);
            result.put(HTTPResponseMessage.STATUS.getValue(), HTTPResponseMessage.ERROR);

            return result;
        }

        if (!ApplicationUtils.isValidAccountNumber(accountNumber)) {

            result.put(HTTPResponseMessage.MESSAGE.getValue(), HTTPResponseMessage.BAD_ACCOUNT_NUMBER_FORMAT);
            result.put(HTTPResponseMessage.STATUS.getValue(), HTTPResponseMessage.ERROR);

            return result;
        }


        return result;
    }
}
