package com.larus.itiszuccante.service;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Badge;
import com.larus.itiszuccante.repository.BadgeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
public class BadgeServiceTest {

    @Autowired
    BadgeRepository repository;

    @Autowired
    BadgeService service;

    Badge badge = new Badge("Recluta", "Primo step");

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void testCreate() {
        Badge createdBadge = service.create(badge);
        Optional<Badge> result = repository.findById(createdBadge.getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getName()).isEqualTo(badge.getName());
        assertThat(result.orElse(null).getDescription()).isEqualTo(badge.getDescription());
    }

    @Test
    public void testRead() {
        Badge b = new Badge();
        b.setName("Recluta");
        b.setDescription("Sei al primo step");
        Optional<Badge> result = service.read(repository.save(b).getId());
        assertThat(result).isPresent();
        assertThat(result.orElse(null).getName()).isEqualTo("Recluta");
        assertThat(result.orElse(null).getDescription()).isEqualTo("Sei al primo step");
    }

    @Test
    public void testUpdate() {
        Badge createdBadge = repository.save(badge);
        assertEquals("Recluta", createdBadge.getName());
        assertEquals("Primo step", createdBadge.getDescription());
        createdBadge.setDescription("Sei al primo step");
        Badge updatedBadge = service.update(createdBadge);
        assertEquals("Sei al primo step", updatedBadge.getDescription());
    }

    @Test
    public void testDelete() {
        Badge createdBadge = repository.save(badge);
        assertEquals(1, repository.findAll().size());
        Optional<Badge> findById = repository.findById(createdBadge.getId());
        assertThat(findById).isPresent();
        service.delete(createdBadge.getId());
        Optional<Badge> readBadge = repository.findById(createdBadge.getId());
        assertThat(readBadge).isNotPresent();
        assertEquals(0, repository.findAll().size());
    }

}
