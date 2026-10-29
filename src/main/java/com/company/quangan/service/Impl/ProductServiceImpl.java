package com.company.quangan.service.Impl;

import com.company.quangan.config.CloudinaryConfig;
import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.entity.ProductCategory;
import com.company.quangan.payload.entity.ProductImage;
import com.company.quangan.payload.entity.User;
import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.create.ProductImageDTO;
import com.company.quangan.payload.request.update.UpdateProductRequest;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;
import com.company.quangan.repository.ProductCategoryRepository;
import com.company.quangan.repository.ProductImageRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
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

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    CloudinaryService cloudinaryService;



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
        for (Product product : products) {
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

    @Override
    public List<ProductResponse> getProductsWithStatus(boolean status) {
        List<Product> products = productRepository.findProductWithStatusTrue(status);

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
    }

    @Override
    public List<ProductImageDTO> getAllImageByProduct(int productId) {
        List<ProductImage> productImages = productImageRepository.getProductImageByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Image not found with Product Id: " + productId));

        List<ProductImageDTO> productImageDTOS = productImages.stream()
                .map(image -> {
                    ProductImageDTO productImageDTO = mapper.map(image, ProductImageDTO.class);
                    productImageDTO.setImagePath(image.getPath());

                    if (image.getProduct() != null) {
                        productImageDTO.setProductId(image.getProduct().getProductId());
                    }

                    return productImageDTO;
                })
                .collect(Collectors.toList());

        return productImageDTOS.isEmpty() ? null : productImageDTOS;
    }

    @Override
    public List<ProductImageDTO> getAllImageByProductStatusTrue(int productId) {
        List<ProductImage> productImages = productImageRepository.getProductImageByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Image not found with Product Id: " + productId));

        List<ProductImageDTO> productImageDTOS = productImages.stream()
                .filter(ProductImage :: isStatus)
                .map(image -> {
                    ProductImageDTO productImageDTO = mapper.map(image, ProductImageDTO.class);
                    productImageDTO.setImagePath(image.getPath());

                    if (image.getProduct() != null) {
                        productImageDTO.setProductId(image.getProduct().getProductId());
                    }

                    return productImageDTO;
                })
                .collect(Collectors.toList());

        return productImageDTOS.isEmpty() ? null : productImageDTOS;
    }

    @Override
    public void deleteProductImage(String imageCode) throws IOException {
        ProductImage productImage = productImageRepository.getProductImageByImageCode(imageCode)
                .orElseThrow(() -> new RuntimeException("Image not found with Product Image Code: " + imageCode));

        String publicId = extractPublicId(productImage.getPath());

        Map result = cloudinaryService.deleteFile(publicId);
        if ("ok".equals(result.get("result"))) {
            productImage.setStatus(false);
            productImageRepository.save(productImage);
        } else {
            throw new RuntimeException("Failed to delete image from Cloudinary: " + result);
        }
    }

    @Override
    public List<ProductImageDTO> uploadMultipleProductImages(MultipartFile[] multipartFiles, int productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with Id: " + productId));

        Integer imageCount = productImageRepository.countImageByProduct(productId);
        if(imageCount == null) imageCount = 0;
        if(imageCount >= 10) throw new RuntimeException("Maximum of 10 images already uploaded for this product.");

        List<ProductImageDTO> uploadedImages = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            File file = convertMultiPartToFile(multipartFile);
            Map uploadResult = cloudinaryService.uploadFile(file);

            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setPath(uploadResult.get("url").toString());
            productImage.setProductImageCode(uploadResult.get("public_id").toString());
            productImage.setStatus(true);

            ProductImage savedProductImage = productImageRepository.save(productImage);

            ProductImageDTO productImageDTO = mapper.map(savedProductImage, ProductImageDTO.class);
            productImageDTO.setProductId(savedProductImage.getProduct().getProductId());
            productImageDTO.setImagePath(savedProductImage.getPath());

            uploadedImages.add(productImageDTO);
        }

        return uploadedImages;
    }

    @Override
    public ProductImageDTO getProductImageByCode(String imageCode) {
        ProductImage productImage = productImageRepository.getProductImageByImageCode(imageCode)
                .orElseThrow(() -> new RuntimeException("Image not found with Product Image Code: " + imageCode));

        ProductImageDTO productImageDTO = mapper.map(productImage, ProductImageDTO.class);
        productImageDTO.setProductId(productImage.getProduct().getProductId());
        productImageDTO.setImagePath(productImage.getPath());

        return productImageDTO;
    }

    private String extractPublicId(String imageUrl) {
        // Kiểm tra định dạng URL
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }

        // Ví dụ: https://res.cloudinary.com/demo/image/upload/v1234567890/public_id.jpg
        // Trích xuất public_id "v1234567890/public_id"
        int startIndex = imageUrl.lastIndexOf("/") + 1; // Vị trí bắt đầu của public_id
        int endIndex = imageUrl.lastIndexOf("."); // Vị trí kết thúc trước đuôi file
        if (startIndex < 0 || endIndex < 0 || startIndex >= endIndex) {
            throw new RuntimeException("Invalid image URL format: " + imageUrl);
        }
        return imageUrl.substring(startIndex, endIndex); // Trả về public_id
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
