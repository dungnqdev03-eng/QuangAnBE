package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
