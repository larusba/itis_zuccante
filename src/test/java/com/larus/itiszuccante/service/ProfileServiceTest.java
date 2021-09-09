package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import com.larus.itiszuccante.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.repository.ProfileRepository;

@IntegrationTest
public class ProfileServiceTest {

	@Autowired
	ProfileRepository repository;

	@Autowired
	ProfileService service;

	PersonalFootprint personalFootprint = new PersonalFootprint();
	Vehicle vehicle = new Vehicle();
	Recycling recycling = new Recycling();
	Profile profile = new Profile(personalFootprint, vehicle, recycling);

	@BeforeEach
	public void init() {
		personalFootprint.setMobilityVehicles("MEDIUM");
		vehicle.setFuelType(FuelType.BIODIESEL);
		recycling.setOrganicWaste(0);
		repository.deleteAll();
	}

	@Test
	public void testCreate() {
		Profile createdPro = service.create(profile);
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
		updatedFootprint.setMobilityVehicles("LOW");
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
