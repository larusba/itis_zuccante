package com.larus.itiszuccante.service;

import java.util.List;
import java.util.Optional;

import com.larus.itiszuccante.domain.Suggestion;

public interface SuggestionService {

	Suggestion create(Suggestion s);
	Optional<Suggestion> read(String id);
	Suggestion update(Suggestion s);
	void delete(String id);
	List<Suggestion> findByType(String type);

}