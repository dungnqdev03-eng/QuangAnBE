package com.company.quangan.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    private int postId;

    private LocalDate createdDate;

    private String postTitle;

    private String postContent;

    private boolean status;

    private int userId;

    private int postCategoryId;
}
