package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.domain.PostType;
import com.larus.itiszuccante.repository.PostRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class PostServiceIT {

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostService service;

    Post post = new Post(PostType.REPORT, "6138c1ae94cc8d093a86ef4f", "Some content");

    @Test
    public void testCreate() {
        Post createdPs = service.create(post);

        Optional<Post> result = repository.findById(createdPs.getId());

        assertThat(result).isPresent();
        assertThat(result.orElse(null).getContent()).isEqualTo(post.getContent());
        assertThat(result.orElse(null).getType()).isEqualTo(post.getType());
        assertThat(result.orElse(null).getGroup()).isEqualTo(post.getGroup());
    }

    @Test
    public void testRead() {
        Post g = new Post();
        g.setType(PostType.REPORT);
        g.setGroup("6138c1ae94cc8d093a86ef4f");
        g.setContent("Some content");

        Post result = service.read(repository.save(g).getId()).get();

        assertEquals("6138c1ae94cc8d093a86ef4f", result.getGroup());
        assertEquals("Some content", result.getContent());
        assertEquals("6138c1ae94cc8d093a86ef4f", result.getGroup());
    }

    @Test
    public void testUpdate() {
        Post createdPost = repository.save(post);

        assertEquals("6138c1ae94cc8d093a86ef4f", createdPost.getGroup());
        assertEquals("Some content", createdPost.getContent());

        createdPost.setContent("Some more content");
        Post updatedPost = service.update(createdPost);

        assertEquals("Some more content", updatedPost.getContent());
    }

    @Test
    public void testDelete() {
        Post createdPost = repository.save(post);

        assertEquals(1, repository.findAll().size());
        Optional<Post> findById = repository.findById(createdPost.getId());

        assertThat(findById).isPresent();
        service.delete(createdPost.getId());
        Optional<Post> readPost = repository.findById(createdPost.getId());
        assertThat(readPost).isNotPresent();
        assertEquals(0, repository.findAll().size());
    }
}
