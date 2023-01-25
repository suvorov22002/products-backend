package com.afriland.packageservices.requirement;

import com.afriland.packageservices.entity.Product;
import org.hibernate.mapping.Any;
import org.springframework.http.ResponseEntity;

public interface IModelController {

    public <Any> Any create();


    public <Any> Any update();

    public <Any> Any find();

    public <Any> Any delete();
}
