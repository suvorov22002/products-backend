package com.afriland.packageservices.service;

import com.afriland.packageservices.entity.Pack;
import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackRepository packageRepository;

    public List<Pack> findAll() {

        return packageRepository.findAll();
    }

    public Pack create(Pack pack) {

        return packageRepository.save(pack);
    }

    public List<Product> findByCode(String code) {

        return packageRepository.findByCode(code);
    }
}
