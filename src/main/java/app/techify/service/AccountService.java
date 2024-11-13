package app.techify.service;

import app.techify.entity.Account;
import app.techify.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    public Account createAccount(Account account) {
        if (account.getPasswordHash() != null) {
            account.setPasswordHash(passwordEncoder.encode(account.getPasswordHash()));
        }
        if (account.getRole() == null) {
            account.setRole("CUSTOMER");
        }
        return accountRepository.save(account);
    }
}
