package com.example.banking.Repository;

import com.example.banking.domain.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface UserLevelRepository extends JpaRepository<UserLevel,Long> {

    UserLevel  findUserLevelByLevelName(String levelName);

}
