package com.afriland.packageservices.utils;

import com.afriland.packageservices.entity.OTP;
import com.afriland.packageservices.enums.MaxCodeLength;
import com.afriland.packageservices.enums.PhoneNumberPattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationUtils {

    @Value("${application.sms.user}") static String user;

    @Value("${application.sms.password}") static String password;


    @Value("${application.sms.senderid}") static String senderId;


    public static void setUser(String user) {
        ApplicationUtils.user = user;
    }

    public static Boolean codeIsCorrect(String code) {

        //return (code.matches("^[a-zA-Z0-9]+$") && code.length() > 0 && code.length() <= MaxCodeLength.PRODUCT.value) ? true : false;

        return true;
    }

    public static Boolean isValidPhoneNumber(String phoneNumber, String format) {

        List<String> startedFormat = new ArrayList<>();

        switch (PhoneNumberPattern.valueOf(format)) {

            case MTN:
                startedFormat = Arrays.asList("67", "68", "650", "651", "652", "653", "654");
                break;

            case ORANGE:
                startedFormat = Arrays.asList("69", "655", "656", "657", "658", "659");
                break;

            default:
                break;
        }

        return ( (phoneNumber.length()==12 && phoneNumber.startsWith("237")) &&
                (startedFormat.contains(phoneNumber.substring(3, 5)) || startedFormat.contains(phoneNumber.substring(3, 6))) ) ? true : false;

    }

    public static Boolean isValidAccountNumber(String accountNumber) {

        return true;
    }

    public static Integer generateOTP() throws Exception {

        Random random = new Random();
        Integer otp = 100000 + random.nextInt(900000);

        return otp;
    }

    public static Boolean sendSMS(String phoneNumber, String message) {

        RestTemplate restTemplate = new RestTemplate();

        final String baseURL = "https://smsvas.com/bulk/public/index.php/api/v1/sendsms";
        String sms = "leonel, Bonjour";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL)
                .queryParam("user", "leonelbojiko@gmail.com")
                .queryParam("password","leoneljessie")
                .queryParam("senderid", "DRI_FISRT")
                .queryParam("sms", message)
                .queryParam("mobiles", phoneNumber);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        /*ResponseEntity<Object> httpResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Object.class);

        Object body = httpResponse.getBody();

        System.out.println(body);
        System.out.println(httpResponse.getStatusCode());


        return httpResponse.getStatusCode() == HttpStatus.OK ? true : false;*/
        return true;
    }
}
