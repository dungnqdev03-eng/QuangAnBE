package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p from Product p WHERE p.productCategory.productCategoryId = :cId")
    List<Product> findProductByProductCategory(@Param("cId") int cId);


    @Query("SELECT p from Product p WHERE p.status = :status")
    List<Product> findProductWithStatusTrue(@Param("status") boolean stauts);

    @Query("SELECT p from Product p WHERE p.productId = :pId")
    Product findByProductId(@Param("pId") int pId);
}
