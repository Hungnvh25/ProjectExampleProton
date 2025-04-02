package com.example.banking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RedisHash("bankTransaction")
public class BankTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bankTransactionId;

    @ManyToMany(mappedBy = "bankTransactions",fetch = FetchType.EAGER)
    List<Card> cards;

    LocalDateTime transactionDate = LocalDateTime.now();
    Double amount;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    statusType status;


    LocalDateTime createdAt = LocalDateTime.now();

    @Temporal(TemporalType.DATE)
    LocalDate createDate = LocalDate.now();
    Long formNumberCard;
    Long toNumberCard;

    public enum TransactionType {
        DEPOSIT, // Gui tien
        WITHDRAWAL, // Rut tien
        TRANSFER, // Chuyen tien
        RECEIVE // Nhan tien
    }

    public enum statusType {
        SUCCESS,
        FAILED,
        PENDING
    }


}