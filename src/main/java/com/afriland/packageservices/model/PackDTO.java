package com.afriland.packageservices.model;

import com.afriland.packageservices.entity.Product;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class PackDTO {

    private UUID id;
    private String name;
    private String code;

    private Set<ProductDTO> products;

    public String toString() {

        return this.name + " (" + this.code + ")";
    }
}
