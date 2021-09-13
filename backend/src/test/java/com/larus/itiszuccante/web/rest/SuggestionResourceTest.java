package com.larus.itiszuccante.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Suggestion;
import com.larus.itiszuccante.repository.SuggestionRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
public class SuggestionResourceTest {
	
    @Autowired
    private SuggestionRepository repository;

    @Autowired
    private MockMvc restUserMockMvc;
    
    private Suggestion suggestion = new Suggestion("Riciclo", "Ricicla");
    
    @BeforeEach
    public void init() {
    	repository.deleteAll();
    }
	
    @Test
	public void testCreate() throws Exception {
		
		assertEquals(0, repository.findAll().size());
		
        restUserMockMvc
        .perform(
            post("/api/suggestions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(suggestion))
        )
        .andExpect(status().isCreated());
        
        assertEquals(1, repository.findAll().size());
		
	}
    
    @Test
    public void testRead() throws Exception {
    	Suggestion createdSug = repository.save(suggestion);
    	restUserMockMvc
        .perform(get("/api/suggestions/{id}", createdSug.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.type").value(suggestion.getType()))
        .andExpect(jsonPath("$.description").value(suggestion.getDescription()));
    }
    
    @Test
    public void testReadFail() throws Exception {
    	restUserMockMvc
        .perform(get("/api/suggestions/{id}", "ID not existing"))
        .andExpect(status().isNotFound());
    }
    
    @Test
    public void testUpdate() throws Exception {
    	Suggestion createdSug = repository.save(suggestion);
    	createdSug.setDescription("Ricicliamo");
        restUserMockMvc
        .perform(
            put("/api/suggestions/{id}", createdSug.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdSug))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.type").value(createdSug.getType()))
        .andExpect(jsonPath("$.description").value(createdSug.getDescription()));
    }
    
    @Test
    public void testDelete() throws Exception {
    	Suggestion createdSug = repository.save(suggestion);
    	restUserMockMvc
        .perform(delete("/api/suggestions/{id}", createdSug.getId()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

}
