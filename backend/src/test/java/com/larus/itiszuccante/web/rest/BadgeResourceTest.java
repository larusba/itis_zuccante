package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Badge;
import com.larus.itiszuccante.repository.BadgeRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
public class BadgeResourceTest {

    @Autowired
    private BadgeRepository repository;

    @Autowired
    private MockMvc restUserMockMvc;

    private Badge badge = new Badge("Recluta", "Primo step");

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void testCreate() throws Exception {

        assertEquals(0, repository.findAll().size());

        restUserMockMvc
            .perform(
                post("/api/badges").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(badge))
            )
            .andExpect(status().isCreated());

        assertEquals(1, repository.findAll().size());

    }

    @Test
    public void testRead() throws Exception {
        Badge createdBadge = repository.save(badge);
        restUserMockMvc
            .perform(get("/api/badges/{id}", createdBadge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name").value(badge.getName()))
            .andExpect(jsonPath("$.description").value(badge.getDescription()));
    }

    @Test
    public void testReadFail() throws Exception {
        restUserMockMvc
            .perform(get("/api/badges/{id}", "ID not existing"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
        Badge createdBadge = repository.save(badge);
        createdBadge.setDescription("Sei al primo step");
        restUserMockMvc
            .perform(
                put("/api/badges/{id}", createdBadge.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdBadge))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name").value(createdBadge.getName()))
            .andExpect(jsonPath("$.description").value(createdBadge.getDescription()));
    }

    @Test
    public void testDelete() throws Exception {
        Badge createdBadge = repository.save(badge);
        restUserMockMvc
            .perform(delete("/api/badges/{id}", createdBadge.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

}

