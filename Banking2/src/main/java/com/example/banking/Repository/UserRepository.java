package com.example.banking.Repository;


import com.example.banking.domain.User;
import com.example.banking.domain.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);

    User findUsersByUserName(String userName);

    List<User> findByUserNameContaining(String userName);
    List<User> findUsersByUserLevel(UserLevel userLevel);


}
