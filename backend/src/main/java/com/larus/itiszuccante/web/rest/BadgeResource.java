package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Suggestion;
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

import com.larus.itiszuccante.domain.Badge;
import com.larus.itiszuccante.service.BadgeService;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/badges")
public class BadgeResource {

	@Autowired
	private BadgeService service;

	@PostMapping
	public ResponseEntity<Badge>  create(@RequestBody Badge b) {

        return new ResponseEntity<Badge>(service.create(b), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Badge> read(@PathVariable String id) {

        return ResponseUtil.wrapOrNotFound(service.read(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Badge> update(@RequestBody Badge b, @PathVariable String id) {
		b.setId(id);
		return new ResponseEntity<Badge> (service.update(b), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Badge> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<Badge>(HttpStatus.NO_CONTENT);
	}

}
