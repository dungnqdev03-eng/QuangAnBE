package com.company.quangan.payload.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column
    private Date createdDate;

    @Column(columnDefinition = "nvarchar(max)")
    private String postTitle;

    @Column(columnDefinition = "nvarchar(max)")
    private String postContent;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postCategoryId")
    private PostCategory postCategory;

    @OneToMany(mappedBy = "post")
    private Collection<PostImage> postImages;
}
