package org.example.repository;

import org.example.entity.Chatting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chatting, String> {

}
