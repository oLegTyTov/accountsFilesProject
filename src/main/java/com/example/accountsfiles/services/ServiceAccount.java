package com.example.accountsfiles.services;

import com.example.accountsfiles.entities.Account;
import com.example.accountsfiles.repositories.RepositoryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceAccount implements UserDetailsService {

    private final RepositoryAccount repositoryAccount;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServiceAccount(RepositoryAccount repositoryAccount, PasswordEncoder passwordEncoder) {
        this.repositoryAccount = repositoryAccount;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addAccount(Account account) {
        if (!repositoryAccount.existsByName(account.getName())) {
            // Шифруємо пароль перед збереженням
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            repositoryAccount.save(account);
            return true;
        } else {
            return false;
        }
    }
public boolean deleteAccount(String name, String password) {
    // Знайти акаунт за ім'ям
    Account account = repositoryAccount.findByName(name);
    
    // Перевірити, чи акаунт існує та чи паролі співпадають
    if (account != null && passwordEncoder.matches(password, account.getPassword())) {
        // Видалити акаунт за ім'ям
        repositoryAccount.deleteByName(name);
        return true;
    } else {
        return false;
    }
}


    public boolean existsByNameAndPassword(String name, String password) {
        Account account = repositoryAccount.findByName(name);
        return account != null && passwordEncoder.matches(password, account.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repositoryAccount.findByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                account.getName(), account.getPassword(), new ArrayList<>()
        );
    }
}
