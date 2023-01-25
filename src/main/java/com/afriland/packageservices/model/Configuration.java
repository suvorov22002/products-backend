package com.afriland.packageservices.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Value("${application.sms.user}")
    private static String user;
}
