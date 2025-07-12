package com.devnilesh.journalapp.service;

import com.devnilesh.journalapp.controller.entry.JournalEntry;
import com.devnilesh.journalapp.controller.entry.User;
import com.devnilesh.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService { 


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userEntryService;



   @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            User user = userEntryService.findByUserName(userName);
            user.getJournalEntries().add(saved);
            //  user.setPassword(null);
            userEntryService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("somthing gone a wrong!");
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {
        //OverLoaded math for saveEntry for put method of journalEntry.
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName)
    {
        User user = userEntryService.findByUserName(userName);
        user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userEntryService.saveEntry(user);
         journalEntryRepository.deleteById(id);
    }

}


// controller----->services------>repository