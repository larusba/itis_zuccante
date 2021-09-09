package com.larus.itiszuccante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.larus.itiszuccante.domain.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    //List<Group> findAllByParticipant(User user);
}
