package com.company.quangan.payload.request.create;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
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
