package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    @Query("select pi from ProductImage pi where pi.product.productId = :pId")
    Optional<List<ProductImage>> getProductImageByProductId(@Param("pId") int id);

    @Query("select pi from ProductImage pi WHERE pi.productImageCode = :code")
    Optional<ProductImage> getProductImageByImageCode(@Param("code") String code);

    @Query(value = "SELECT COUNT(product_image_id) FROM product_image WHERE product_id = :id AND status = true", nativeQuery = true)
    Integer countImageByProduct(@Param("id") int id);
}
