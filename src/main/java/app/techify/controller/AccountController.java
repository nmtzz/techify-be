package app.techify.controller;

import app.techify.entity.Account;
import app.techify.entity.Customer;
import app.techify.service.AccountService;
import app.techify.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestParam String email, @RequestParam String passwordHash, @RequestParam String fullName) {
        Account newAccount = Account.builder()
                .email(email)
                .passwordHash(passwordHash)
                .build();
        Account savedAccount = accountService.createAccount(newAccount);
        Customer newCustomer = Customer.builder()
                .account(savedAccount)
                .fullName(fullName)
                .build();
        customerService.createCustomer(newCustomer);
        return ResponseEntity.ok().build();
    }
}
