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

import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileResource {
	
	@Autowired
	ProfileService service;
	
	@PostMapping
	Profile create(@RequestBody Profile p) {
		System.out.println(p);
		return service.create(p);
	}
	
	@GetMapping("/{id}")
	Profile read(@PathVariable String id) {
		return service.read(id);
	}
	
	@PutMapping("/{id}")
	Profile update(@RequestBody Profile p, @PathVariable String id) {
		p.setId(id);
		return service.update(p);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable String id) {
		service.delete(id);
	}
	
}