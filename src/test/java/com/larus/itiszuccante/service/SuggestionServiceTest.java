package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.repository.SuggestionRepository;

@IntegrationTest
public class SuggestionServiceTest {
	
	@Autowired
	SuggestionRepository repository;
	
	@Autowired
	SuggestionService service;
	
	Suggestion suggestion = new Suggestion("Riciclo", "Ricicla");
	
	@BeforeEach
	public void init() {
		repository.deleteAll();
	}
	
	@Test
	public void testCreate() {
		Suggestion createdSug = service.create(suggestion);
		Optional<Suggestion> result = repository.findById(createdSug.getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getType()).isEqualTo(suggestion.getType());
        assertThat(result.orElse(null).getDescription()).isEqualTo(suggestion.getDescription());
	}
	
	@Test
	public void testRead() {
		Suggestion s = new Suggestion();
		s.setDescription("Getta la carta nel cestino giallo");
		s.setType("Riciclo");
		Suggestion result = service.read(repository.save(s).getId());
		assertEquals("Riciclo", result.getType());
		assertEquals("Getta la carta nel cestino giallo", result.getDescription());
	}
	
	@Test
	public void testUpdate() {
		Suggestion createdSug = repository.save(suggestion);
		assertEquals("Riciclo", createdSug.getType());
		assertEquals("Ricicla", createdSug.getDescription());
		createdSug.setDescription("Getta la carta nel cestino giallo");
		Suggestion updatedSug = service.update(createdSug);
		assertEquals("Getta la carta nel cestino giallo", updatedSug.getDescription());
	}
	
	@Test
	public void testDelete() {
		Suggestion createdSug = repository.save(suggestion);
		assertEquals(1, repository.findAll().size());
		Optional<Suggestion> findById = repository.findById(createdSug.getId());
		assertThat(findById).isPresent();
		service.delete(createdSug.getId());
		Optional<Suggestion> readSug = repository.findById(createdSug.getId());
		assertThat(readSug).isNotPresent();
		assertEquals(0, repository.findAll().size());
	}

}