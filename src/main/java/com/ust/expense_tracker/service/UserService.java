package com.ust.expense_tracker.service;

import com.ust.expense_tracker.model.User;
import com.ust.expense_tracker.repository.ExpenseRepo;
import com.ust.expense_tracker.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    
    private final UserRepo userRepo;
    private final ExpenseRepo expenseRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return false;
        }
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTotalExpense(0.0);
        userRepo.save(user);
        return true;
    }
}
