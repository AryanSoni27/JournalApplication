package com.aryan.journalApp.Repository;

import com.aryan.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface journalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {


}
