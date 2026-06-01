package com.company.quangan.service;

import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.update.UpdateProductRequest;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    String createProduct(CreateProductRequest createProductRequest);
    String createCategory(CreateProductCategoryRequest createProductCategoryRequest);
    String updateProduct(UpdateProductRequest updateProductRequest);
    String deleteProduct(int productId);
    ProductResponse getProductById(int ProductId);
    List<ProductResponse> getProducts();
    List<ProductCategoryResponse> getProductCategoryResponse();
    List<ProductResponse> getProductByCateId(int cId);
}
