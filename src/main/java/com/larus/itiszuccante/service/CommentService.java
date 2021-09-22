package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment create(Comment p);
    Optional<Comment> read(String id);
    Comment update(Comment p);
    void delete(String id);

    List<Comment> findAllByPost(String postid);
}
