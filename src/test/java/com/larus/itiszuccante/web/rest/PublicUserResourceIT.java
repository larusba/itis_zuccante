package com.larus.itiszuccante.web.rest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.domain.BehaviourType;
import com.larus.itiszuccante.domain.CarType;
import com.larus.itiszuccante.domain.FuelType;
import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.domain.Vehicle;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;

/**
 * Integration tests for the {@link UserResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
class PublicUserResourceIT {

    private static final String DEFAULT_LOGIN = "johndoe";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc restUserMockMvc;

    private User user;

    @BeforeEach
    public void initTest() {
        user = UserResourceIT.initTestUser(userRepository);
    }

    @Test
    void getAllPublicUsers() throws Exception {
        // Initialize the database
        userRepository.save(user);

        // Get all the users
        restUserMockMvc
            .perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].email").doesNotExist())
            .andExpect(jsonPath("$.[*].imageUrl").doesNotExist())
            .andExpect(jsonPath("$.[*].langKey").doesNotExist());
    }

    @Test
    void getAllAuthorities() throws Exception {
        restUserMockMvc
            .perform(get("/api/authorities").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").value(hasItems(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN)));
    }
    
    @Test
    void getBehavioursTest() throws Exception {
    	Behaviour newBehaviour = new Behaviour();
        newBehaviour.setDistance(100);
        newBehaviour.setType(BehaviourType.CAR_TRIP);
        newBehaviour.setEmission(0.01);
        Vehicle vehicle = new Vehicle();
        vehicle.setCarType(CarType.SMALL);
        vehicle.setFuelType(FuelType.BIODIESEL);
        Profile profile = new Profile();
        profile.setVehicle(vehicle);
		user.setProfile(profile);
		ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();
		behaviours.add(newBehaviour);
		user.setBehaviour(behaviours);
        user = userRepository.save(user);
        
        restUserMockMvc
        .perform(get("/api/users/{id}/behaviours", user.getId()).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").value(Arrays.asList(hasItems(newBehaviour).toString())));
    }
}
