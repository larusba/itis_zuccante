package com.larus.itiszuccante.service.impl;

import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.repository.PostRepository;
import com.larus.itiszuccante.service.PostService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPostService implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post create(Post p) {
        return repository.save(p);
    }

    @Override
    public Optional<Post> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Post update(Post p) {
        return repository.save(p);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
