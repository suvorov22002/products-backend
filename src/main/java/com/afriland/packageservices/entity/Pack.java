package com.afriland.packageservices.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "APP_PACKAGE")
public class Pack {

    public Pack() {}

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "PACKAGE_NAME", nullable = false)
    private String name;

    @Column(name = "PACKAGE_CODE", nullable = false)
    private String code;


    @ManyToMany
    @JoinTable(
            name = "app_packages_products",
            joinColumns = @JoinColumn(name = "PACKAGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<Product> products;

    @OneToMany(mappedBy = "pack")
    List<Subscription> subscriptions;
}
