package com.example.accountsfiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accountsfiles.entities.Account;
import com.example.accountsfiles.entities.MyFile;
import java.util.List;


@Repository
public interface RepositoryMyFile extends JpaRepository<MyFile,Long>{
List<MyFile> findByAccount(Account account);
MyFile findByIdAndAccount(Long id, Account account);
boolean existsByIdAndAccount(Long id, Account account);
}
