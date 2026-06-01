package com.company.quangan.payload.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePostRequest {
    private int postId;

    private String postTitle;

    private String postContent;

    private int categoryId;
}
