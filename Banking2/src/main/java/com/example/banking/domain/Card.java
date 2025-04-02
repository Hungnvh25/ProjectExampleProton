package com.example.banking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RedisHash("card")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;


    @OneToOne(mappedBy = "card",cascade = CascadeType.ALL)
    Balance balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false, unique = true)
    private Long cardNumber;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinTable(name = "Card_BankTransaction"
            ,joinColumns = @JoinColumn(name = "card_id")
            ,inverseJoinColumns = @JoinColumn(name = "bankTransaction_id"))
    List<BankTransaction> bankTransactions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CardType cardType;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

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