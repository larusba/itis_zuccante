package com.larus.itiszuccante.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.domain.BehaviourType;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.repository.BehaviourRepository;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN, username = "user-test")
@IntegrationTest
public class BehaviourResourceTest {

    @Autowired
    private BehaviourRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc restUserMockMvc;

    private Date date = new Date(0);

    private Behaviour behaviour = new Behaviour(BehaviourType.RECYCLING, date);

    @BeforeEach
    public void init() {
        repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testCreate() throws Exception {

        assertEquals(0, repository.findAll().size());

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        user.setLogin(principal.getName());
        user.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        user = userRepository.save(user);

        restUserMockMvc
            .perform(
                post("/api/{userId}/behaviours", user.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(behaviour))
            )
            .andExpect(status().isCreated());

        assertEquals(1, repository.findAll().size());

    }

    @Test
    public void testRead() throws Exception {
        Behaviour createdBehaviour = repository.save(behaviour);
        restUserMockMvc
            .perform(get("/api/behaviours/{id}", createdBehaviour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.type").value(behaviour.getType().toString()))
            .andExpect(jsonPath("$.date").value("1970-01-01T00:00:00.000+00:00"));
    }

    @Test
    public void testReadFail() throws Exception {
        restUserMockMvc
            .perform(get("/api/behaviours/{id}", "ID not existing"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
        Date dateTest = new Date(100000);
        Behaviour createdBehaviour = repository.save(behaviour);
        createdBehaviour.setDate(dateTest);
        restUserMockMvc
            .perform(
                put("/api/behaviours/{id}", createdBehaviour.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdBehaviour))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.type").value(createdBehaviour.getType().toString()))
            .andExpect(jsonPath("$.date").value("1970-01-01T00:01:40.000+00:00"));
    }

    @Test
    public void testDelete() throws Exception {
        Behaviour createdBehaviour = repository.save(behaviour);
        restUserMockMvc
            .perform(delete("/api/behaviours/{id}", createdBehaviour.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

}
