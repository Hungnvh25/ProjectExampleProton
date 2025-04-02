package com.example.banking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLevel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long levelId;

    @OneToMany(mappedBy = "userLevel",fetch = FetchType.LAZY)
    List<User> users;

    String levelName;
    Integer cardLimit;
    Double dailyTransferLimit;
}