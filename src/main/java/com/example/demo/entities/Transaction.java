package com.example.demo.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@jakarta.persistence.Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public Long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
    


}
