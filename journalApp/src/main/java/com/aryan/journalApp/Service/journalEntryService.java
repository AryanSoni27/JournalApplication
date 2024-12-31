package com.aryan.journalApp.Service;

import com.aryan.journalApp.Entity.JournalEntry;
import com.aryan.journalApp.Entity.User;
import com.aryan.journalApp.Repository.journalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving entry",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }
        return removed;
    }


}


//Controller --> Service --> Repository
//This is the calling Structure of Spring Boot Project
//You can do whole code in controller but the above method is the best practice of
//writing code in spring boot project