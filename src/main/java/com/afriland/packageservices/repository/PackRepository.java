package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.Pack;
import com.afriland.packageservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PackRepository extends JpaRepository<Pack, UUID> {

    @Query("select p from Pack p where p.code = :packCode")
    List<Product> findByCode(@Param("packCode") String packCode);
}
