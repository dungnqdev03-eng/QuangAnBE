package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
