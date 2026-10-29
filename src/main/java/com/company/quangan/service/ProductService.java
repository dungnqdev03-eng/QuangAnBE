package com.company.quangan.service;

import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.create.ProductImageDTO;
import com.company.quangan.payload.request.update.UpdateProductRequest;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    List<ProductResponse> getProductsWithStatus(boolean status);
    List<ProductImageDTO> getAllImageByProduct(int productId);
    List<ProductImageDTO> getAllImageByProductStatusTrue(int productId);
    void deleteProductImage(String imageCode) throws IOException;
    List<ProductImageDTO> uploadMultipleProductImages(MultipartFile[] multipartFiles, int productId) throws IOException;
    ProductImageDTO getProductImageByCode(String imageCode);

}
