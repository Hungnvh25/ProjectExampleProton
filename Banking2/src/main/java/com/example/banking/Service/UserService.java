package com.example.banking.Service;

import com.example.banking.Repository.BalanceRepository;
import com.example.banking.Repository.CardRepository;
import com.example.banking.Repository.UserLevelRepository;
import com.example.banking.Repository.UserRepository;
import com.example.banking.domain.Balance;
import com.example.banking.domain.Card;
import com.example.banking.domain.User;
import com.example.banking.domain.UserLevel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

    UserRepository userRepository;
//    PasswordEncoder passwordEncoder;
    CardRepository cardRepository;
    BalanceRepository balanceRepository;
    UserLevelRepository userLevelRepository;

    public User findUserByUserName(String userName){
       return this.userRepository.findByUserName(userName).orElseThrow(()
       -> new RuntimeException("User not found"));
    }

    public boolean existsByUserName(String userName){
        return this.userRepository.existsByUserName(userName);
    }
    public boolean existsByEmail(String Email){
        return this.userRepository.existsByEmail(Email);
    }
    public List<User> findByAllUser(){
        return this.userRepository.findAll();
    }

    public Page<User> findByAllUser(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public User findUserById(Long id){
        return  this.userRepository.findById(id).orElseThrow(()->new RuntimeException("User not fount"));
    }
//    public void CreateUser(User request){
//        User user = new User();
//        user.setUserName(request.getUserName());
//        user.setFullName(request.getFullName());
//        user.setEmail(request.getEmail());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setRole(request.getRole());
//        user.setUserLevel(request.getUserLevel());
//        user.setUserLevel(request.getUserLevel());
//        user.setPasswordHash(this.passwordEncoder.encode(request.getPasswordHash()));
//        this.userRepository.save(user);
//
//        Card card = Card.builder()
//                .user(user)
//                .cardType(Card.CardType.DEBIT)
//                .status(Card.CardStatus.ACTIVE)
//                .expiryDate(LocalDate.now().plusYears(5))
//                .build();
//        card = this.cardRepository.save(card);
//
//        Balance balance = Balance.builder()
//                .card(card)
//                .availableBalance(0.0)
//                .holdBalance(0.0)
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        this.balanceRepository.save(balance);
//
//    }

    public User saveUser(User user){
        try{
           return userRepository.save(user);
        }catch(Exception e){
            System.out.println("Lỗi khi tạo user: " + e.getMessage());
            throw e;
        }
    }


    public void deleteUser(Long id){
        User user = this.findUserById(id);
        user.getCards().clear();
        UserLevel userLevel = user.getUserLevel();
        userLevel.getUsers().remove(userLevel);
        this.userRepository.deleteById(id);
    }


    public List<User> findByUserNameLike(String userName){
        return this.userRepository.findByUserNameContaining(userName);
    }

    public List<User> findUserByLevelName(UserLevel userLevel){
        return this.userRepository.findUsersByUserLevel(userLevel);
    }

}
