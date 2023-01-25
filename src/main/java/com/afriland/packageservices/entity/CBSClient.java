package com.afriland.packageservices.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "APP_CBSCLIENT")
public class CBSClient {

    public CBSClient() {};

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "MATRICULE", nullable = false)
    private String matricule;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNo;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String accountName;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Column(name = "SEX", nullable = false)
    private String sexe;

    @Column(name = "ADDRESS", nullable = false)
    private String adresse;

    @Column(name = "PLACE_OF_BIRTH", nullable = false)
    //private String dataNaissance;
    private String lieuNaissance;

    @Column(name = "MANAGER_CODE", nullable = false)
    private String codeGestionnaire;

    @ElementCollection
    @Column(name = "ACCOUNTS", nullable = false)
    private List<String> comptes;

    @ElementCollection
    @Column(name = "PHONE_NUMBERS", nullable = false)
    private List<String> telephones;

    @ElementCollection
    @Column(name = "EMAILS_ADDRESS", nullable = false)
    private List<String> adresseMails;

    @OneToMany(
            mappedBy = "cbsClient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OTP> otpList = new ArrayList<>();

    @OneToOne(mappedBy = "cbsClient")
    private Subscriber subscriber;


    public String toString() {

        return getMatricule() + " - " + getAccountName() + " - " + getAccountNo();
    }
}
