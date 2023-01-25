package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
