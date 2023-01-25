package com.afriland.packageservices.dto;

import com.afriland.packageservices.entity.CBSClient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OtpDTO {

    private UUID id;

    private String otp;

    private LocalDateTime createdAt;

    private CBSClientDTO cbsClient;
}
