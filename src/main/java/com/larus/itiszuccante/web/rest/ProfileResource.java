package com.larus.itiszuccante.web.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.service.ProfileService;

import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/profiles")
public class ProfileResource {

	@Autowired
	ProfileService service;

	@PostMapping
	public ResponseEntity<Profile> create(@RequestBody Profile p) {
		return new ResponseEntity<Profile>(service.create(p), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profile> read(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(service.read(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profile> update(@RequestBody Profile s, @PathVariable String id) {
		s.setId(id);
		return new ResponseEntity<Profile>(service.update(s), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Profile> delete(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
	}

}
