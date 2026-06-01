package com.company.quangan.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    private int productId;
    private String productName;
    private Date createdTime;
    private String description;
    private double price;
    private int quantity;
    private String origin;
    private String brand;
    private int categoryId;
    private int userId;
}
