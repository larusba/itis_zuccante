package com.larus.itiszuccante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.larus.itiszuccante.domain.Behaviour;

public interface BehaviourRepository extends MongoRepository<Behaviour, String> {

}
