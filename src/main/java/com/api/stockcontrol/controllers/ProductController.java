package com.api.stockcontrol.controllers;

import com.api.stockcontrol.dtos.ProductDTO;
import com.api.stockcontrol.models.Product;
import com.api.stockcontrol.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping("/products")
    public ResponseEntity<Product> save(@RequestBody @Valid ProductDTO productDTO) {
        var product = new Product();
        BeanUtils.copyProperties(productDTO, product);

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }
}