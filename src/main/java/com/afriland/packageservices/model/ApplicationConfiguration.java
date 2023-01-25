package com.afriland.packageservices.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationConfiguration {

    @Value("${application.sms.user}")
    private static String user;



    public static String getUser() {

        return user;
    }
}
