package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
