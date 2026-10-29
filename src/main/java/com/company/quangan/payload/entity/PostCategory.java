package com.company.quangan.payload.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postCategoryId;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String postCategoryName;

    @Column
    private boolean status;

    @OneToMany(mappedBy = "postCategory")
    private Collection<Post> posts;

}
