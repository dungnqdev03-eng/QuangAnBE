package com.company.quangan.controller;

import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Slf4j
//@CrossOrigin(origins = "http://localhost:5173")
//@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateProductRequest createProductRequest) {

        String response = productService.createProduct(createProductRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
