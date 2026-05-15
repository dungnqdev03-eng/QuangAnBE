package com.company.quangan.payload.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookMarkId;

    @Column
    private Date createdTime;

    @Column(columnDefinition = "nvarchar(max)")
    private String message;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
