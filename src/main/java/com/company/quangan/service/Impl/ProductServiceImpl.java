package com.company.quangan.service.Impl;

import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.entity.ProductCategory;
import com.company.quangan.payload.entity.User;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.update.ProductResponse;
import com.company.quangan.repository.ProductCategoryRepository;
import com.company.quangan.repository.ProductRepository;
import com.company.quangan.repository.UserRepository;
import com.company.quangan.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    ModelMapper mapper;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    UserRepository userRepository;



    @Override
    public String createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setProductName(createProductRequest.getProductName());
        product.setBrand(createProductRequest.getBrand());
        product.setCreatedTime(LocalDate.now());
        product.setOrigin(createProductRequest.getOrigin());
        product.setPrice(createProductRequest.getPrice());
        product.setDescription(createProductRequest.getDescription());
        product.setQuantity(createProductRequest.getQuantity());
        product.setStatus(true);

        ProductCategory productCategory = productCategoryRepository.getReferenceById(createProductRequest.getCategoryId());
        product.setProductCategory(productCategory);

        User user = userRepository.getReferenceById(createProductRequest.getUserId());
        product.setUser(user);

        productRepository.save(product);
        return "success";
    }
}
