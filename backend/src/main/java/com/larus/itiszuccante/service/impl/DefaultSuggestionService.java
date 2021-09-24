package com.larus.itiszuccante.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.repository.SuggestionRepository;
import com.larus.itiszuccante.service.SuggestionService;

@Service
public class DefaultSuggestionService implements SuggestionService {

	@Autowired
	private SuggestionRepository repository;

	@Override
	public Suggestion create(Suggestion s) {
		return repository.save(s);
	}

	@Override
	public Optional<Suggestion> read(String id) {
		return repository.findById(id);
	}

	@Override
	public Suggestion update(Suggestion s) {
		return repository.save(s);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public List<Suggestion> findByType(String type) {
	    if(type.equals("ALL"))
		    return repository.findAll();
	    else
	        return repository.findByType(type);
	}

}
