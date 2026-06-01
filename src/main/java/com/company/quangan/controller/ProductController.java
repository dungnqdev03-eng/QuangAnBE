package com.company.quangan.controller;

import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.response.ApiResponse;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;
import com.company.quangan.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/product")
@Slf4j
@CrossOrigin(origins = "http://localhost:3001")
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

    @PostMapping("create_category")
    public ResponseEntity<String> createCategory(@RequestBody CreateProductCategoryRequest createProductCategoryRequest) {

        String response = productService.createCategory(createProductCategoryRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<ProductCategoryResponse>>> getAllProductCategory() {
        List<ProductCategoryResponse> productCategoryResponses = productService.getProductCategoryResponse();
        ApiResponse<List<ProductCategoryResponse>> response = ApiResponse.<List<ProductCategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched product category")
                .data(productCategoryResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{cId}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProductByCategory(@PathVariable int cId) {
        List<ProductResponse> productResponses = productService.getProductByCateId(cId);
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched product by category")
                .data(productResponses)
                .build();
        return ResponseEntity.ok(response);
    }
}
