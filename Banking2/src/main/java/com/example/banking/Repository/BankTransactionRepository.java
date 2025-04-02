package com.example.banking.Repository;


import com.example.banking.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
    List<BankTransaction> findBankTransactionByCreateDate(LocalDate date);

}
