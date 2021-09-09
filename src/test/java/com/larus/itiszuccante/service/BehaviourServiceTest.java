package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.repository.BehaviourRepository;

@IntegrationTest
public class BehaviourServiceTest {

    @Autowired
    BehaviourRepository repository;

    @Autowired
    BehaviourService service;

    Date date = new Date();

    Behaviour behaviour = new Behaviour("car-trip", date);

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void testCreate() {
        Behaviour createdBehaviour = service.create(behaviour);
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
        b.setType("car-trip");
        Behaviour result = service.read(repository.save(b).getId());
        assertEquals("car-trip", result.getType());
        assertEquals(date, result.getDate());
    }

    @Test
    public void testUpdate() {
        Date dateTest = new Date();
        Behaviour createdBehaviour = repository.save(behaviour);
        assertEquals("car-trip", createdBehaviour.getType());
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

}
