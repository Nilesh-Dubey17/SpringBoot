package com.devnilesh.journalapp.repository;

import com.devnilesh.journalapp.controller.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
