package com.example.banking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RedisHash("user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    //    @NotEmpty(message = "Name cannot be empty")
    String fullName;

    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    String email;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
//    @NotNull(message = "phone not null")
    String phoneNumber;

    @NotNull(message = "UserName not null")
    String userName;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String passwordHash;

    private String token;

    private LocalDateTime tokenExpiry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    public enum Role {
        CUSTOMER,
        EMPLOYEE,
        ADMIN
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    List<Card> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level")
    UserLevel userLevel;
}