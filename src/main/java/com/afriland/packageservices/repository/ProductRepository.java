package com.afriland.packageservices.repository;

import com.afriland.packageservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select p from Product p where p.code = :productCode")
    List<Product> findByCode(@Param("productCode") String productCode);

}
