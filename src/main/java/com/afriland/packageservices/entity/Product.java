package com.afriland.packageservices.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "APP_PRODUCT")
public class Product {

    public Product() {}

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_CODE", nullable = false)
    private String code;

}
