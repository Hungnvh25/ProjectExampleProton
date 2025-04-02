package com.example.banking.domain;

import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDTO {

    private Long cardId;


    Long balanceId;


    Long userId;


    private Long cardNumber;



    private Card.CardType cardType;

    private LocalDate expiryDate;

    private Card.CardStatus status;
    @PrePersist
    private void generateCardNumber() {
        this.cardNumber = System.currentTimeMillis() % 1_000_000_000L;
    }

    public enum CardType {
        DEBIT,
        CREDIT
    }

    public enum CardStatus {
        ACTIVE,
        INACTIVE
    }
}
