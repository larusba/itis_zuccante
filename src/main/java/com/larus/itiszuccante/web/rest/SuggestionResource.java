package com.larus.itiszuccante.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.service.SuggestionService;


@RestController
@RequestMapping("/api/suggestions")
public class SuggestionResource {

	@Autowired
	private SuggestionService service;

	@PostMapping
	public Suggestion create(@RequestBody Suggestion s) {
		return service.create(s);
	}

	@GetMapping("/{id}")
	public Suggestion read(@PathVariable String id) {
		return service.read(id);
	}

	@PutMapping("/{id}")
	public Suggestion update(@RequestBody Suggestion s, @PathVariable String id) {
		s.setId(id);
		return service.update(s);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping
	public List<Suggestion> findByType(@RequestParam String type) {
		return service.findByType(type);
	}

}
