package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Post;
import java.util.Optional;

public interface PostService {
    Post create(Post p);
    Optional<Post> read(String id);
    Post update(Post p);
    void delete(String id);
}
