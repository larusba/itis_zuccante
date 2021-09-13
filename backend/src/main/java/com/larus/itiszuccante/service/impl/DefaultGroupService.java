package com.larus.itiszuccante.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.repository.GroupRepository;
import com.larus.itiszuccante.service.GroupService;

@Service
public class DefaultGroupService implements GroupService {

    @Autowired
    private GroupRepository repository;

    @Override
    public Group create(Group s) {
        return repository.save(s);
    }

    @Override
    public Optional<Group> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Group> update(Group s) {
        return Optional.of(repository.save(s));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
    /*@Override
    public List<Group> findAllByParticipant(User user) {
        return repository.findAllByParticipant(user);
    }*/
}
