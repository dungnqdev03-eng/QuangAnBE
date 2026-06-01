package com.company.quangan.service.Impl;

import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.entity.ProductCategory;
import com.company.quangan.payload.entity.User;
import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.update.UpdateProductRequest;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;
import com.company.quangan.repository.ProductCategoryRepository;
import com.company.quangan.repository.ProductRepository;
import com.company.quangan.repository.UserRepository;
import com.company.quangan.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper mapper;

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



    @Override
    public String createCategory(CreateProductCategoryRequest createProductCategoryRequest) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName(createProductCategoryRequest.getProductCategoryName());
        productCategory.setStatus(true);

        productCategoryRepository.save(productCategory);
        return "success!";
    }

    @Override
    public String updateProduct(UpdateProductRequest updateProductRequest) {
        return "";
    }

    @Override
    public String deleteProduct(int productId) {
        Product product = productRepository.getReferenceById(productId);
        product.setStatus(false);

        productRepository.save(product);
        return "success!";
    }

    @Override
    public ProductResponse getProductById(int ProductId) {
        Product product = productRepository.getReferenceById(ProductId);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setDescription(product.getDescription());
        productResponse.setOrigin(product.getOrigin());
        productResponse.setBrand(product.getBrand());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCreatedTime(productResponse.getCreatedTime());
        productResponse.setCategoryId(product.getProductCategory().getProductCategoryId());

        return productResponse;
    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();
        for(int i = 0; i<products.size(); i++){
            Product product = products.get(0);

            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductId(product.getProductId());
            productResponse.setProductName(product.getProductName());
            productResponse.setDescription(product.getDescription());
            productResponse.setOrigin(product.getOrigin());
            productResponse.setBrand(product.getBrand());
            productResponse.setPrice(product.getPrice());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setCreatedTime(productResponse.getCreatedTime());
            productResponse.setCategoryId(product.getProductCategory().getProductCategoryId());

            productResponses.add(productResponse);
        }

        return productResponses;

//        List<ProductResponse> productResponses = products.stream()
//                .map(product -> {
//                    ProductResponse productResponse = mapper.map(product, ProductResponse.class);
//
//                    if (product.getProductCategory() != null) {
//                        productResponse.setCategoryId(mapper.map(product.getProductCategory(), ProductCategoryResponse.class));
//                    }
//                    if (product.getUser() != null) {
//                        productResponse.setUser(mapper.map(product.getUser(), AccountDTO.class));
//                    }
//
//                    return productDTO;
//                })
//                .collect(Collectors.toList());
//
//        return productDTOS.isEmpty() ? null : productDTOS;
    }

    @Override
    public List<ProductCategoryResponse> getProductCategoryResponse() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();

        return productCategories.stream()
                .map(category -> mapper.map(category, ProductCategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductByCateId(int cId) {
        List<Product> products = productRepository.findProductByProductCategory(cId);
        return products.stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }
}
