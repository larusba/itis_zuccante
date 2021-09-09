package com.larus.itiszuccante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.repository.BehaviourRepository;
import com.larus.itiszuccante.service.BehaviourService;

@Service
public class DefaultBehaviourService implements BehaviourService {

    @Autowired
    private BehaviourRepository repository;

    @Override
    public Behaviour create(Behaviour b) {
        return repository.save(b);
    }

    @Override
    public Behaviour read(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Behaviour update(Behaviour b) {
        return repository.save(b);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}
