package com.larus.itiszuccante.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.larus.itiszuccante.domain.*;
import com.larus.itiszuccante.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.repository.ProfileRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
public class ProfileResourceTest {

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc restUserMockMvc;

    private PersonalFootprint personalFootprint = new PersonalFootprint();
    private Vehicle vehicle = new Vehicle();
    private Recycling recycling = new Recycling();

    private Profile profile = new Profile();

    @BeforeEach
    public void init() {
    	personalFootprint.setMobilityVehicles(MobilityVehicles.MEDIUM);
    	vehicle.setFuelType(FuelType.BIODIESEL);
    	recycling.setOrganicWaste(0);
    	repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
	public void testCreate() throws Exception {

		assertEquals(0, repository.findAll().size());

		User user = new User();
		user.setLogin("login");
		user.setPassword("b133a0c0e9bee3be20163d2ad31d6248db292aa6dcb1ee087a2aa50e0fc7");
        User save = userRepository.save(user);

        restUserMockMvc
        .perform(
            post("/api/{userId}/profiles", save.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(profile))
        )
        .andExpect(status().isCreated());

        assertEquals(1, repository.findAll().size());

	}

    @Test
    public void testRead() throws Exception {
    	Profile createdPro = repository.save(profile);
    	restUserMockMvc
        .perform(get("/api/profiles/{id}", createdPro.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.personalFootprint").value(profile.getPersonalFootprint()))
        .andExpect(jsonPath("$.vehicle").value(profile.getVehicle()))
    	.andExpect(jsonPath("$.recycling").value(profile.getRecycling()));
    }

    @Test
    public void testReadFail() throws Exception {
    	restUserMockMvc
        .perform(get("/api/profiles/{id}", "ID not existing"))
        .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
    	Profile createdPro = repository.save(profile);
    	PersonalFootprint updatedFootprint = new PersonalFootprint();
    	updatedFootprint.setMobilityVehicles(MobilityVehicles.LOW);
    	createdPro.setPersonalFootprint(updatedFootprint);
        restUserMockMvc
        .perform(
            put("/api/profiles/{id}", createdPro.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(createdPro))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.personalFootprint.mobilityVehicles").value(createdPro.getPersonalFootprint().getMobilityVehicles().toString()))
        .andExpect(jsonPath("$.vehicle").value(createdPro.getVehicle()))
        .andExpect(jsonPath("$.recycling").value(createdPro.getRecycling()));
    }

    @Test
    public void testDelete() throws Exception {
    	Profile createdPro = repository.save(profile);
    	restUserMockMvc
        .perform(delete("/api/profiles/{id}", createdPro.getId()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

}
