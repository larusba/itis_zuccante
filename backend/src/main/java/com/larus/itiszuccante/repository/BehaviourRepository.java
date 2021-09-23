package com.larus.itiszuccante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.larus.itiszuccante.domain.Behaviour;
import org.springframework.stereotype.Repository;

@Repository
public interface BehaviourRepository extends MongoRepository<Behaviour, String> {

}
