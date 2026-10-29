package com.company.quangan.payload.request.create;

import com.company.quangan.payload.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    private String address;
    private String fullName;
    private String password;
    private boolean status;
    private String phone;
    private String email;
    private boolean sex;
}
