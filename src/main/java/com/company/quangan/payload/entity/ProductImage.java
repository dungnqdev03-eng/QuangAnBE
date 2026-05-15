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
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productImageId;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String productImageCode;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
