package com.example.accountsfiles.services;

import com.example.accountsfiles.entities.Account;
import com.example.accountsfiles.entities.MyFile;
import com.example.accountsfiles.repositories.RepositoryAccount;
import com.example.accountsfiles.repositories.RepositoryMyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.IOException;

@Service
public class ServiceMyFile {

    private final RepositoryMyFile repositoryMyFile;
    private final RepositoryAccount repositoryAccount;
    @Autowired
    public ServiceMyFile(RepositoryMyFile repositoryMyFile,RepositoryAccount repositoryAccount) {
        this.repositoryMyFile = repositoryMyFile;
        this.repositoryAccount=repositoryAccount;

    }
            private Account getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return repositoryAccount.findByName(username);
    }

    public void saveFile(MultipartFile file) throws IOException {
        MyFile myFile = new MyFile();
        myFile.setFile_data(file.getBytes());
        myFile.setAccount(getCurrentUser());//юзер який залогінився
        repositoryMyFile.save(myFile);
    }

    public MyFile getFile(Long id) {
        return repositoryMyFile.findById(id).get();//must be cheked in controller because can be null
    }
    public boolean deleteFile(Long id)
    {
        if(repositoryMyFile.existsByIdAndAccount(id, getCurrentUser()))
        {
            repositoryMyFile.deleteById(id);
            return true;    
        }
        else{
        return false;
        }
    
    }
    public List<MyFile> getAllFiles()
    {
    Account account=getCurrentUser();
    return repositoryMyFile.findByAccount(account);
    }
    public MyFile getFile(Long id,String nameString) {
        Account account=repositoryAccount.findByName(nameString);
        return repositoryMyFile.findByIdAndAccount(id,account);//must be cheked in controller because can be null
    }
}
