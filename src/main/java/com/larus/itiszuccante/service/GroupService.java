package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.User;
import java.util.List;

public interface GroupService {
    Group create(Group g);
    Group read(String id);
    Group update(Group g);
    void delete(String id);

    List<Group> findAllByParticipant(User user);
}
