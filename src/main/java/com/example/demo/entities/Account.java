package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    private int customerID;
    @Column(name = "accountno")
    private long accountNo; 
    @Column(name = "ifsccode")
    private long ifscCode; 
    @Column(name = "balance")
    private int balance;
    @Column(name = "password")
    private String password;
 
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(long ifscCode) {
		this.ifscCode = ifscCode;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

   
}
