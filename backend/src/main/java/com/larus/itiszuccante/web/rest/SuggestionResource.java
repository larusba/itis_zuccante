package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import java.util.List;


@RestController
@RequestMapping("/api/suggestions")
public class SuggestionResource {

	@Autowired
	private SuggestionService service;

	@PostMapping
	public ResponseEntity<Suggestion> create(@RequestBody Suggestion s) {
		return new ResponseEntity<Suggestion>(service.create(s), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Suggestion> read(@PathVariable String id) {
         return ResponseUtil.wrapOrNotFound(service.read(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Suggestion> update(@RequestBody Suggestion s, @PathVariable String id) {
		s.setId(id);
		return new ResponseEntity<Suggestion>(service.update(s), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Suggestion> delete(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<Suggestion>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{type}")
	public List<Suggestion> findByType(@PathVariable String type) {
		return service.findByType(type);
	}
	
	@GetMapping
	public List<Suggestion> findAll() {
		return service.findAll();
	}

}
