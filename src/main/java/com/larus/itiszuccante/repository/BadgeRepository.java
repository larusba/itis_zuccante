package com.larus.itiszuccante.repository;


import com.larus.itiszuccante.domain.Badge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends MongoRepository<Badge, String> {

}
