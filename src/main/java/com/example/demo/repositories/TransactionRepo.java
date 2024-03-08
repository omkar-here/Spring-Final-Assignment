package com.example.demo.repositories;

import com.example.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
