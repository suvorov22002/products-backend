package com.afriland.packageservices.service;

import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.model.ProductDTO;
import com.afriland.packageservices.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {

        return productRepository.findAll();
    }

    public List<Product> findByCode(String code) {

        return productRepository.findByCode(code);
    }


    public void deleteById(UUID id) {

        productRepository.deleteById(id);
    }

    public Product create(Product product) {

        return productRepository.save(product);
    }
}
