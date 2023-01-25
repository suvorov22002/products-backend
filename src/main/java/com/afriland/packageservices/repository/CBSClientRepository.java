package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.CBSClient;
import com.afriland.packageservices.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CBSClientRepository extends JpaRepository<CBSClient, UUID> {

    @Query("select c from CBSClient c where c.matricule = :clientCode")
    List<CBSClient> findByCode(@Param("clientCode") String clientCode);

}
