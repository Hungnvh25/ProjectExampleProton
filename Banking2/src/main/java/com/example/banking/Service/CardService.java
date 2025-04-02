package com.example.banking.Service;

import com.example.banking.Repository.CardRepository;
import com.example.banking.domain.Balance;
import com.example.banking.domain.Card;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CardService {
    CardRepository cardRepository;
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "request-queue-card")
    public void processRequest(Long cardId, Message message) throws JMSException {
        String correlationId = message.getJMSCorrelationID();

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found: " + cardId));

        jmsTemplate.convertAndSend("response-queue-card", card, responseMessage -> {
            responseMessage.setJMSCorrelationID(correlationId);
            return responseMessage;
        });
        System.out.println("Processed and sent response for card ID: " + cardId);
    }

    public Card createCard(Card card){
        Balance balance = Balance.builder()
                .card(card)
                .availableBalance(0.0)
                .holdBalance(0.0)
                .updatedAt(LocalDateTime.now())
                .build();
        card.setBalance(balance);
        return this.cardRepository.save(card);
    }


    public Card getCardById(Long id){
        return  this.cardRepository.findCardByCardId(id);
    }

}
