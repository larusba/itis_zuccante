package com.larus.itiszuccante.repository;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    List<Group> findAllByParticipant(User user);
}
