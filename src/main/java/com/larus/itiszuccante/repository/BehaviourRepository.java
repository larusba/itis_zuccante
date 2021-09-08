package com.larus.itiszuccante.repository;

import com.larus.itiszuccante.domain.Behaviour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BehaviourRepository extends MongoRepository<Behaviour, String> {

}
