package com.afriland.packageservices.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "APP_SUBSCRIBER")
public class Subscriber {


    public Subscriber() {}

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE", nullable = true)
    private String code;

    @ElementCollection
    @Column(name = "ACCOUNTS", nullable = true)
    private List<String> accountNumbers;

    @ElementCollection
    @Column(name = "PHONE_NUMBERS", nullable = true)
    private List<String> phoneNumbers;

    @OneToMany(mappedBy = "subscriber")
    List<Subscription> subscriptions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CBS_CLIENT", referencedColumnName = "id")
    private CBSClient cbsClient;





    /*public void addOTP(OTP otp) {

        otpList.add(otp);
        otp.setSubscriber(this);
    }

    public void removeOTP(OTP otp) {

        otpList.remove(otp);
        otp.setSubscriber(null);
    }*/
}
