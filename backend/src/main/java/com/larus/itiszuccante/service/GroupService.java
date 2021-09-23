package com.larus.itiszuccante.service;

import java.util.Optional;

import com.larus.itiszuccante.domain.Group;

public interface GroupService {
    Group create(Group g);
    Optional<Group> read(String id);
    Optional<Group> update(Group g);
    void delete(String id);
    //List<Group> findAllByParticipant(User user);
}
