package com.api.stockcontrol.controllers;

import com.api.stockcontrol.dtos.ProductDTO;
import com.api.stockcontrol.models.Product;
import com.api.stockcontrol.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOne(@PathVariable UUID id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid ProductDTO productDTO) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productDTO, productModel);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
    }
}