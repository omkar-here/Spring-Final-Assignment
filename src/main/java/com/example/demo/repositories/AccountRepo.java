package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNo(long accountNo);

    List<Account> findByBalanceLessThan(int balance);

    List<Account> findByBalanceGreaterThan(int balance);
}
