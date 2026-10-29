package com.company.quangan.payload.request.create;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageDTO {
    int productImageId;

    String productImageCode;

    String imagePath;

    boolean status;

    int productId;
}
