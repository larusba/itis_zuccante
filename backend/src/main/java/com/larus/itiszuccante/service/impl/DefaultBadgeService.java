package com.larus.itiszuccante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Badge;
import com.larus.itiszuccante.repository.BadgeRepository;
import com.larus.itiszuccante.service.BadgeService;

import java.util.Optional;

@Service
public class DefaultBadgeService implements BadgeService {

    @Autowired
    private BadgeRepository repository;

    @Override
    public Badge create(Badge b) {
        return repository.save(b);
    }

    @Override
    public Optional<Badge> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Badge update(Badge b) {
        return repository.save(b);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}
