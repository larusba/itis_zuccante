package com.larus.itiszuccante.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;


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

	@GetMapping
	public List<Suggestion> findByType(@RequestParam String type) {
		return service.findByType(type);
	}

}
