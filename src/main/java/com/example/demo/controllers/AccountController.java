package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Account;
import com.example.demo.entities.Transaction;
import com.example.demo.repositories.AccountRepo;
import com.example.demo.repositories.TransactionRepo;

@RestController
public class AccountController {
	@Autowired
    TransactionRepo transactionRepo;
	
	
    @Autowired
    AccountRepo repo;

    // A - Register
    @PostMapping("/register")
    public String createAccount(@RequestBody Account e) {
        if (repo.existsById(e.getCustomerID()))
            return "Account with given ID already exists";
        repo.save(e);
        return "Successfully added new Account";
    }

    // B - Login
    @GetMapping("/login")
    public boolean login(@RequestParam int customerID, @RequestParam String password) {
        Optional<Account> optionalAccount = repo.findById(customerID);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return account.getPassword().equals(password);
        }
        return false;
    }

    // C - Change password
    @PutMapping("/changepwd/{cid}/{oldPassword}/{newPassword}")
    public String changePassword(@PathVariable int cid, @PathVariable String oldPassword, @PathVariable String newPassword) {
        Optional<Account> optionalAccount = repo.findById(cid);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getPassword().equals(oldPassword)) {
            	if(oldPassword.equals(newPassword)) return "Old Password and New Password cannot be same";
                account.setPassword(newPassword);
                repo.save(account);
                return "Password changed successfully";
            } else {
                return "Old password is incorrect";
            }
        }
        return "Account not found";
    }

 // D - Transfer
    @PostMapping("/transfer")
    public String transfer(@RequestBody Transaction t) {
        Optional<Account> optionalFromAccount = repo.findByAccountNo(t.getFromAccountId());
        Optional<Account> optionalToAccount = repo.findByAccountNo(t.getToAccountId());

        if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {
            Account fromAccount = optionalFromAccount.get();
            Account toAccount = optionalToAccount.get();

            // Check if fromAccount has sufficient balance
            if (fromAccount.getBalance() >= t.getAmount()) {
                fromAccount.setBalance((int)(fromAccount.getBalance() - t.getAmount()));
                repo.save(fromAccount);

                // Set fromAccountId and toAccountId for the Transaction object
                t.setFromAccountId(fromAccount.getAccountNo());
                t.setToAccountId(toAccount.getAccountNo());

                // Save the transaction
                transactionRepo.save(t);

                // Add amount to toAccount
                
                toAccount.setBalance((int)(toAccount.getBalance() + t.getAmount()));
                repo.save(toAccount);

                return "Transfer successful";
            } else {
                return "Insufficient balance in the sender's account";
            }
        } else {
            return "One or both of the accounts do not exist";
        }
    }



    // E - Check balance
    @GetMapping("/balance/{accountNo}")
    public int checkBalance(@PathVariable long accountNo) {
        Optional<Account> optionalAccount = repo.findByAccountNo(accountNo);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return account.getBalance();
        }
        return -1; 
    }

    // F - Accounts below given balance
    @GetMapping("/accounts/below")
    public List<Account> getAccountsBelowBalance(@RequestParam int amount) {
        return repo.findByBalanceLessThan(amount);
    }

    // G - Accounts above given balance
    @GetMapping("/accounts/above")
    public List<Account> getAccountsAboveBalance(@RequestParam int amount) {
        return repo.findByBalanceGreaterThan(amount);
    }

    // H - Delete account (optional)
    @DeleteMapping("/account/{customerId}")
    public String deleteAccount(@PathVariable int customerId) {
        repo.deleteById(customerId);
        return "Account deleted successfully";
    }

    // I - Fetch account details by customer ID (optional)
    @GetMapping("/account/{customerId}")
    public Optional<Account> getAccountDetails(@PathVariable int customerId) {
        return repo.findById(customerId);
    }
}
