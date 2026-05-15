package com.company.quangan.payload.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import com.company.quangan.payload.Enum.EOrderType;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false, columnDefinition = "nvarchar(max)")
    private String oderCode;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate orderDate;

    @Column(nullable = false, columnDefinition = "nvarchar(max)")
    private String orderName;

    @Column
    private boolean status;

    @Column(nullable = false)
    private EOrderType type;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
