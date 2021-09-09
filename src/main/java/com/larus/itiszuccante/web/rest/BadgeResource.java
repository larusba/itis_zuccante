package com.larus.itiszuccante.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/badges")
public class BadgeResource {

	@Autowired
	private BadgeService service;

	@PostMapping
	public Badge create(@RequestBody Badge b) {

        return service.create(b);
	}

	@GetMapping("/{id}")
	public Badge read(@PathVariable String id) {

        return service.read(id);
	}

	@PutMapping("/{id}")
	public Badge update(@RequestBody Badge b, @PathVariable String id) {
		b.setId(id);
		return service.update(b);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {

        service.delete(id);
	}

}
