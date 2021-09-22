package com.larus.itiszuccante.service.impl;

import com.larus.itiszuccante.domain.Comment;
import com.larus.itiszuccante.repository.CommentRepository;
import com.larus.itiszuccante.service.CommentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCommentService implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public Comment create(Comment p) {
        return repository.save(p);
    }

    @Override
    public Optional<Comment> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Comment update(Comment p) {
        return repository.save(p);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Comment> findAllByPost(String postid) {
        return repository.findAllByPost(postid);
    }
}
