package com.larus.itiszuccante.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.larus.itiszuccante.domain.Badge;

@Repository
public interface BadgeRepository extends MongoRepository<Badge, String> {

}
