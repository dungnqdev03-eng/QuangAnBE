package com.company.quangan.payload.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postImageId;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false,columnDefinition = "NVARCHAR(255)")
    private String postImageCode;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
}
