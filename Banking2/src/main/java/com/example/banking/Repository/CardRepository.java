package com.example.banking.Repository;

import com.example.banking.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    public Card findCardByCardId(Long id);
    Card findCardByCardNumber(Long cardNumber);




}
