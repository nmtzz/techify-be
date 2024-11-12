package app.techify.controller;

import app.techify.entity.Account;
import app.techify.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok().build();
    }
}
