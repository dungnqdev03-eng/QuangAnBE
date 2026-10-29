package com.company.quangan.payload.request.create;

import com.company.quangan.payload.Enum.EOrderType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    private boolean gender; // "Anh" / "Chị"
    private String fullName;
    private String phone;
    private String email;       // không bắt buộc
    private String address;     // không bắt buộc
    private String note;        // không bắt buộc
    private String productName;
    private int productId;
    private int quantity;
    private long unitPrice;
    private EOrderType paymentMethod;
}
