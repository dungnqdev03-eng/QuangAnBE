package com.company.quangan.payload.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCategoryResponse {
    private int postCategoryId;

    private String postCategoryName;

    private boolean status;
}
