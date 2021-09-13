package com.larus.itiszuccante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.larus.itiszuccante.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {}
