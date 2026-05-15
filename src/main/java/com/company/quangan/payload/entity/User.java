package com.company.quangan.payload.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String userName;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    @Column(columnDefinition = "nvarchar(255)")
    private String fullName;

    @Column
    private String password;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate dob;

    @Column
    private boolean status;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column
    private boolean sex;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Collection<Product> products;

    @OneToMany(mappedBy = "user")
    private Collection<Post> posts;

    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;
}
