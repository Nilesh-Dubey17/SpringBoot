package com.devnilesh.journalapp.service;

import com.devnilesh.journalapp.controller.entry.User;
import com.devnilesh.journalapp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserEntryService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }


    public void saveAdmin(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        userEntryRepository.save(user);
    }

    public void saveUser(User user)
    {
        userEntryRepository.save(user);
    }

    public List<User> getAll()
    {
        return userEntryRepository.findAll();
    }
 //we're going use it in as admin controller


    public User findByUserName(String userName) {
        return userEntryRepository.findByUserName(userName);
    }
}
