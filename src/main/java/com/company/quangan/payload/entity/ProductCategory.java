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
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCategoryId;

    @Column
    private String productCategoryName;

    @Column
    private boolean status;

    @OneToMany(mappedBy = "productCategory")
    private Collection<Product> products;
}
