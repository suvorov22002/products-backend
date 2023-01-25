package com.afriland.packageservices.controller;

import com.afriland.packageservices.entity.Product;
import com.afriland.packageservices.entity.Subscription;
import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.model.PackDTO;
import com.afriland.packageservices.model.ProductDTO;
import com.afriland.packageservices.repository.ProductRepository;
import com.afriland.packageservices.service.ProductService;
import com.afriland.packageservices.utils.ApplicationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper mapper = new ModelMapper();

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<ProductDTO>> all() {

        List<ProductDTO> productDTOList = productService.findAll().stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDTOList);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Object> create(@RequestBody ProductDTO productDTO) {

        Product product = this.mapper.map(productDTO, Product.class);

        Map<String, Object> map = new HashMap<>();

        if (!ApplicationUtils.codeIsCorrect(product.getCode())) {

            map.put("message", HTTPResponseMessage.BAD_VALUE_FORMAT);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }


        if (productService.findByCode(product.getCode()).size() > 0) {

            map.put("message", HTTPResponseMessage.OBJECT_ALREADY_EXISTS);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        Product responseProduct = productService.create(product);
        System.out.println(responseProduct.toString());

        return ResponseEntity.ok(responseProduct);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<Product> update(@RequestBody ProductDTO productDTO) {
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public ResponseEntity<Iterable<ProductDTO>> find(@RequestBody List<UUID> products) {

        List<Product> productList = productRepository.findAllById(products);

        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseEntity<Object> delete(@RequestBody List<UUID> products) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            productRepository.deleteAllById(products);
        }
        catch (Exception e) {

            System.out.println(e.getMessage());

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

    @RequestMapping(method = RequestMethod.POST, value = "/subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody HashMap<String, Object> request) {

        System.out.println(request.toString());


        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
