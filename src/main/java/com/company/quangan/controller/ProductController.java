package com.company.quangan.controller;

import com.company.quangan.payload.request.create.CreateProductCategoryRequest;
import com.company.quangan.payload.request.create.CreateProductRequest;
import com.company.quangan.payload.request.create.OrderRequest;
import com.company.quangan.payload.request.create.ProductImageDTO;
import com.company.quangan.payload.response.ApiResponse;
import com.company.quangan.payload.response.ProductCategoryResponse;
import com.company.quangan.payload.response.ProductResponse;
import com.company.quangan.service.Impl.MailService;
import com.company.quangan.service.OrderService;
import com.company.quangan.service.ProductService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.version}/product")
@Slf4j
@CrossOrigin(origins = "http://localhost:3001")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    MailService mailService;

    @Autowired
    OrderService orderService;

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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct(){
        List<ProductResponse> productResponses = productService.getProducts();
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched all products")
                .data(productResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProductWithStatus(@PathVariable boolean status) {
        List<ProductResponse> productResponses = productService.getProductsWithStatus(status);
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched product by category")
                .data(productResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadImages/{productId}")
    public ResponseEntity<ApiResponse<List<ProductImageDTO>>> uploadProductImages(@RequestParam("files") MultipartFile[] files,
                                                                                  @PathVariable int productId) throws IOException {
        List<ProductImageDTO> productImageDTOs = productService.uploadMultipleProductImages(files, productId);

        ApiResponse<List<ProductImageDTO>> response = ApiResponse.<List<ProductImageDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully uploaded Product Images")
                .data(productImageDTOs)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all_images/{productId}")
    public ResponseEntity<ApiResponse<List<ProductImageDTO>>> getAllImageByProduct(@PathVariable int productId) {
        List<ProductImageDTO> productImageDTOS = productService.getAllImageByProduct(productId);
        ApiResponse<List<ProductImageDTO>> response = ApiResponse.<List<ProductImageDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched Product Images")
                .data(productImageDTOS)
                .build();
        return ResponseEntity.ok(response);
    }



    @GetMapping("/all_images_statusTrue/{productId}")
    public ResponseEntity<ApiResponse<List<ProductImageDTO>>> getAllImageByProductStatusTrue(@PathVariable int productId) {
        List<ProductImageDTO> productImageDTOS = productService.getAllImageByProductStatusTrue(productId);
        ApiResponse<List<ProductImageDTO>> response = ApiResponse.<List<ProductImageDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully fetched Product Images")
                .data(productImageDTOS)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteImage/{imageCode}")
    public ResponseEntity<ApiResponse<String>> deleteProductImage(@PathVariable String imageCode) throws IOException {
        productService.deleteProductImage(imageCode);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully Deleted Product Image")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getImage/{imageCode}")
    public ResponseEntity<ApiResponse<ProductImageDTO>> getProductImageByCode(@PathVariable String imageCode){
        ProductImageDTO productImageDTO = productService.getProductImageByCode(imageCode);
        ApiResponse<ProductImageDTO> response = ApiResponse.<ProductImageDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully Deleted Product Image")
                .data(productImageDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            String result = orderService.createOrder(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Đặt hàng thất bại: " + e.getMessage());
        }
    }
}
