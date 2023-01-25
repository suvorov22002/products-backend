package com.afriland.packageservices.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "APP_SUBSCRIPTION")
public class Subscription {

    public Subscription() {}

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    Pack pack;

    LocalDateTime subscribedAt;
}
