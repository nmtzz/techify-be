package app.techify.service;

import app.techify.dto.UserResponse;
import app.techify.entity.Account;
import app.techify.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Chưa xác thực người dùng");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();

        return UserResponse.builder()
                .fullName(username)
                .role(role)
                .build();
    }
}
