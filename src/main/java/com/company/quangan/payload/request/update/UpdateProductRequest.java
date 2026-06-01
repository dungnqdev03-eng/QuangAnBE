package com.company.quangan.payload.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductRequest {
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String origin;
    private String brand;
    private int categoryId;
}
