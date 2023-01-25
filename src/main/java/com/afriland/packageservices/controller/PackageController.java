package com.afriland.packageservices.controller;


import com.afriland.packageservices.entity.Pack;
import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.model.PackDTO;
import com.afriland.packageservices.model.ProductDTO;
import com.afriland.packageservices.repository.PackRepository;
import com.afriland.packageservices.service.PackageService;
import com.afriland.packageservices.utils.ApplicationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    PackRepository packRepository;
    private ModelMapper mapper = new ModelMapper();


    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<PackDTO>> all() {

        List<PackDTO> packDTOList = packageService.findAll().stream()
                .map(pack -> mapper.map(pack, PackDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(packDTOList);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Object> create(@RequestBody PackDTO packDTO) {

        System.out.println(packDTO);
        Map<String, Object> map = new HashMap<String, Object>();

        if (packageService.findByCode(packDTO.getCode()).size() > 0) {

            map.put("message", HTTPResponseMessage.OBJECT_ALREADY_EXISTS);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        if (!ApplicationUtils.codeIsCorrect(packDTO.getCode())) {

            map.put("message", HTTPResponseMessage.BAD_VALUE_FORMAT);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        Pack pack = packageService.create(mapper.map(packDTO, Pack.class));

        System.out.println(pack.toString());

        return ResponseEntity.ok(pack);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<Pack> update(@RequestBody PackDTO packDTO) {
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public ResponseEntity<Iterable<PackDTO>> find(@RequestBody List<UUID> packs) {

        List<Pack> packList = packRepository.findAllById(packs);

        List<PackDTO> packDTOList = packList.stream()
                .map(pack -> mapper.map(pack, PackDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(packDTOList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseEntity<Object> delete(@RequestBody List<UUID> packs) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            packRepository.deleteAllById(packs);
        }
        catch (Exception e) {

            map.put("message", HTTPResponseMessage.UNABLE_TO_DELETE_OBJECT);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        finally {
            map.put("message", HTTPResponseMessage.OBJECT_SUCCESSFULLY_DELETED);
            map.put("status", HTTPResponseMessage.SUCCESS);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
