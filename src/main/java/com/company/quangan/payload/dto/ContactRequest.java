package com.company.quangan.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContactRequest {
        @NotBlank(message = "Vui lòng nhập họ và tên")
        private String fullName;

        @NotBlank(message = "Vui lòng nhập số điện thoại")
        @Pattern(regexp = "^(0|\\+84)\\d{9}$", message = "Số điện thoại không hợp lệ")
        private String phone;

        private String email;          // không bắt buộc

        private String topic;          // "Báo giá sản phẩm", ...

        @NotBlank(message = "Vui lòng nhập nội dung")
        private String content;
}
