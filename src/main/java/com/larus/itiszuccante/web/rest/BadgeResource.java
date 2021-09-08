package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Badge;
import com.larus.itiszuccante.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
