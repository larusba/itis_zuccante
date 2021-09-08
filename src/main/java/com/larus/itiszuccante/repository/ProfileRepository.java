package com.larus.itiszuccante.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.larus.itiszuccante.domain.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

}
