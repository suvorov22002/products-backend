package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OTPRepository extends JpaRepository<OTP, UUID> {

}
