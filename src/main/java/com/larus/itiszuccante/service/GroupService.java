package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.User;
import java.util.List;
import java.util.Optional;

public interface GroupService {
    Group create(Group g);
    Optional<Group> read(String id);
    Optional<Group> update(Group g);
    void delete(String id);
    //List<Group> findAllByParticipant(User user);
}
