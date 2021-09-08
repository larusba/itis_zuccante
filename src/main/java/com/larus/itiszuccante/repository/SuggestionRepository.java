package com.larus.itiszuccante.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.larus.itiszuccante.domain.Suggestion;

@Repository
public interface SuggestionRepository extends MongoRepository<Suggestion, String> {
	
	List<Suggestion> findByType(String type);

}
