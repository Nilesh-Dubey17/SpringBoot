package com.devnilesh.journalapp.service;

import com.devnilesh.journalapp.controller.entry.User;
import com.devnilesh.journalapp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(User user)
    {
        userEntryRepository.save(user);
    }

    public List<User> getAll()
    {
        return userEntryRepository.findAll();
    }

//    public Optional<User> findById(ObjectId id)
//    {
//        return userEntryRepository.findById(id);
//    }
//
//    public void deleteById(ObjectId id)
//    {
//        userEntryRepository.deleteById(id);
//    }

    public User findByUserName(String userName) {
        return userEntryRepository.findByUserName(userName);
    }
}
