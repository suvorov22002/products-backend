package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {

    @Query("select s from Subscriber s where s.code = :clientCode")
    List<Subscriber> findByCode(@Param("clientCode") String clientCode);


}
