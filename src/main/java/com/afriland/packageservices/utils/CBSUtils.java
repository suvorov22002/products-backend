package com.afriland.packageservices.utils;

import com.afriland.packageservices.dto.CBSClientDTO;
import com.afriland.packageservices.entity.CBSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class CBSUtils {

    @Value("${cbs.service.url}")
    private static String cbsBaseURL;

    @Value("${cbs.service.find-customer}")
    private static String cbsFindCustomerService;


    public static CBSClientDTO getClient(String clientCode) {

        CBSClientDTO cbsClientDTO = new CBSClientDTO();

        try {

            RestTemplate restTemplate = new RestTemplate();

            //final String baseUrl = CBSUtils.cbsBaseURL + CBSUtils.cbsFindCustomerService + "?subcode=" + clientCode;
            final String baseUrl = "http://172.21.88.115:80/internalbussservice/rest/firstpayment/subscriptions/findcustomercbs" + "?subcode=" + clientCode;

            System.out.println("URL ::: " + baseUrl);

            final URI uri = new URI(baseUrl);

            cbsClientDTO = restTemplate.getForObject(uri, CBSClientDTO.class);

            System.out.println(cbsClientDTO);
        }
        catch (Exception e) {
            System.out.println("Unable ta Save The user");
            System.out.println(e.getMessage());
        }

        return cbsClientDTO;
    }
}
