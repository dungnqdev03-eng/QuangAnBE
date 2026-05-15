package com.company.quangan.service;

import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.update.ProductResponse;

public interface ProductService {
    String createProduct (CreateProductRequest createProductRequest);
}
