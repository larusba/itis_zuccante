package com.larus.itiszuccante.repository;

import com.larus.itiszuccante.domain.Comment;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByPost(String post);
}
