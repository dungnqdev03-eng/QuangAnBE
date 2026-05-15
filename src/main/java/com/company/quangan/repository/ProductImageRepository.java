package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
}
