package com.devnilesh.journalapp.repository;

import com.devnilesh.journalapp.controller.entry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

       User findByUserName(String username);
}
