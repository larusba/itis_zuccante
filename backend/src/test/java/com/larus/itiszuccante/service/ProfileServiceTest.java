package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.Optional;

import com.larus.itiszuccante.security.AuthoritiesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.FuelType;
import com.larus.itiszuccante.domain.MobilityVehicles;
import com.larus.itiszuccante.domain.PersonalFootprint;
import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.Recycling;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.domain.Vehicle;
import com.larus.itiszuccante.repository.ProfileRepository;
import com.larus.itiszuccante.repository.UserRepository;
import org.springframework.security.test.context.support.WithMockUser;

@IntegrationTest
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
public class ProfileServiceTest {

	@Autowired
	private ProfileRepository repository;

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private ProfileService service;

    private PersonalFootprint personalFootprint = new PersonalFootprint();
    private Vehicle vehicle = new Vehicle();
    private Recycling recycling = new Recycling();
    private Profile profile = new Profile(personalFootprint, vehicle, recycling);

	@BeforeEach
	public void init() {
		personalFootprint.setMobilityVehicles(MobilityVehicles.MEDIUM);
		vehicle.setFuelType(FuelType.BIODIESEL);
		recycling.setOrganicWaste(0);
		repository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void testCreate() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        user.setLogin(principal.getName());
        user.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        user = userRepository.save(user);
		Profile createdPro = service.create(user.getId(), profile);
		Optional<Profile> result = repository.findById(createdPro.getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getPersonalFootprint().toString()).isEqualTo(profile.getPersonalFootprint().toString());
        assertThat(result.orElse(null).getVehicle().toString()).isEqualTo(profile.getVehicle().toString());
        assertThat(result.orElse(null).getRecycling().toString()).isEqualTo(profile.getRecycling().toString());
	}

	@Test
	public void testRead() {
		Optional<Profile> result = service.read(repository.save(profile).getId());
		assertThat(result).isPresent();
        assertThat(result.orElse(null).getPersonalFootprint().toString()).isEqualTo(personalFootprint.toString());
        assertThat(result.orElse(null).getVehicle().toString()).isEqualTo(vehicle.toString());
        assertThat(result.orElse(null).getRecycling().toString()).isEqualTo(recycling.toString());
	}

	@Test
	public void testUpdate() {
		Profile createdPro = repository.save(profile);
		assertEquals(personalFootprint.toString(), createdPro.getPersonalFootprint().toString());
		assertEquals(vehicle.toString(), createdPro.getVehicle().toString());
		assertEquals(recycling.toString(), createdPro.getRecycling().toString());
		PersonalFootprint updatedFootprint = new PersonalFootprint();
		updatedFootprint.setMobilityVehicles(MobilityVehicles.LOW);
		createdPro.setPersonalFootprint(updatedFootprint);
		Profile updatedPro = service.update(createdPro);
		assertEquals(createdPro.getPersonalFootprint().toString(), updatedPro.getPersonalFootprint().toString());
	}

	@Test
	public void testDelete() {
		Profile createdPro = repository.save(profile);
		assertEquals(1, repository.findAll().size());
		Optional<Profile> findById = repository.findById(createdPro.getId());
		assertThat(findById).isPresent();
		service.delete(createdPro.getId());
		Optional<Profile> readPro = repository.findById(createdPro.getId());
		assertThat(readPro).isNotPresent();
		assertEquals(0, repository.findAll().size());
	}

}
