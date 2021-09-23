package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.domain.BehaviourType;
import com.larus.itiszuccante.domain.CarType;
import com.larus.itiszuccante.domain.FuelType;
import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.domain.Vehicle;
import com.larus.itiszuccante.repository.BehaviourRepository;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@IntegrationTest
public class BehaviourServiceTest {

    @Autowired
    BehaviourRepository repository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BehaviourService service;

    Date date = new Date();

    Behaviour behaviour = new Behaviour(BehaviourType.RECYCLING, date);

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void testCreate() {
    	Principal principal = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findOneByLogin(principal.getName()).orElseThrow();
        user = userRepository.save(user);
        Behaviour createdBehaviour = service.create(user.getId(), behaviour);
        Optional<Behaviour> result = repository.findById(createdBehaviour.getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getType()).isEqualTo(behaviour.getType());
        assertThat(result.orElse(null).getDate()).isEqualTo(behaviour.getDate());
    }

    @Test
    public void testRead() {
        Behaviour b = new Behaviour();
        Date date = new Date();
        b.setDate(date);
        b.setType(BehaviourType.CAR_TRIP);
        Optional<Behaviour> result = service.read(repository.save(b).getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getType()).isEqualTo(BehaviourType.CAR_TRIP);
        assertThat(result.orElse(null).getDate()).isEqualTo(date);
    }


    @Test
    public void testUpdate() {
        Date dateTest = new Date();
        Behaviour createdBehaviour = repository.save(behaviour);
        assertEquals(BehaviourType.RECYCLING, createdBehaviour.getType());
        assertEquals(date, createdBehaviour.getDate());
        createdBehaviour.setDate(dateTest);
        Behaviour updatedBehaviour = service.update(createdBehaviour);
        assertEquals(dateTest, updatedBehaviour.getDate());
    }

    @Test
    public void testDelete() {
        Behaviour createdBehaviour = repository.save(behaviour);
        assertEquals(1, repository.findAll().size());
        Optional<Behaviour> findById = repository.findById(createdBehaviour.getId());
        assertThat(findById).isPresent();
        service.delete(createdBehaviour.getId());
        Optional<Behaviour> readBehaviour = repository.findById(createdBehaviour.getId());
        assertThat(readBehaviour).isNotPresent();
        assertEquals(0, repository.findAll().size());
    }
    
    @Test
    public void testCalculateEmissions() {
        Behaviour newBehaviour = new Behaviour();
        newBehaviour.setDistance(100);
        newBehaviour.setType(BehaviourType.CAR_TRIP);
        Vehicle vehicle = new Vehicle();
        vehicle.setCarType(CarType.SMALL);
        vehicle.setFuelType(FuelType.BIODIESEL);
        Profile profile = new Profile();
        profile.setVehicle(vehicle);
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findOneByLogin(principal.getName()).orElseThrow();
		user.setProfile(profile);
        user = userRepository.save(user);
        newBehaviour = service.create(user.getId(), newBehaviour);
        Optional<Behaviour> result = repository.findById(newBehaviour.getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getEmission()).isEqualTo(0.019874000000000003);
    }

}
