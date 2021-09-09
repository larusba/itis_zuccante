package com.larus.itiszuccante.repository;

import com.larus.itiszuccante.domain.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByCreatedBy(String userid);
    List<Post> findAllByGroup(String groupid);
}
