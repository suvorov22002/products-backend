package com.afriland.packageservices.controller;

import com.afriland.packageservices.requirement.IModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/setting")
public class SettingController implements IModelController {

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public <Any> Any create() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public <Any> Any update() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public <Any> Any find() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public <Any> Any delete() {
        return null;
    }
}
