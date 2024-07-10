package com.example.accountsfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accountsfiles.entities.Account;

@Repository
public interface RepositoryAccount extends JpaRepository<Account,Long>
{
    boolean existsByName(String name);
    void deleteByName(String name);
    Account getByName(String name);
    boolean existsByNameAndPassword(String name, String password);
     Account findByName(String name);
}