package com.example.banking.Controller.client;

import com.example.banking.Service.CardService;
import com.example.banking.Service.UserService;
import com.example.banking.domain.Card;
import com.example.banking.domain.CardDTO;
import com.example.banking.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardApiController {

    private static final Logger logger = LoggerFactory.getLogger(CardApiController.class);

    private final CardService cardService;
    private final UserService userService;

    @PostMapping("/card/create/{userId}")
    public ResponseEntity<CardDTO> createCard(@PathVariable Long userId, @RequestBody CardDTO cardDTO) {
        try {

            User user = userService.findUserById(userId);


            Card card = new Card();
            card.setCardNumber(cardDTO.getCardNumber());
            card.setCardType(cardDTO.getCardType());
            card.setStatus(cardDTO.getStatus());
            card.setExpiryDate(cardDTO.getExpiryDate());
            card.setUser(user);

            Card createdCard = cardService.createCard(card);
            if (createdCard == null) {
                logger.error("Failed to create card for userId: {}", userId);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            CardDTO responseDTO = CardDTO.builder()
                    .cardId(createdCard.getCardId())
                    .cardNumber(createdCard.getCardNumber())
                    .cardType(createdCard.getCardType())
                    .userId(userId)
                    .expiryDate(createdCard.getExpiryDate())
                    .status(createdCard.getStatus())
                    .build();

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}