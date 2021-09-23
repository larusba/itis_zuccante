package com.larus.itiszuccante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.IntegrationTest;
import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.domain.PostType;
import com.larus.itiszuccante.repository.GroupRepository;
import com.larus.itiszuccante.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class GroupServiceTest {

    @BeforeEach
    public void init() {
        repository.deleteAll();
        postRepository.deleteAll();
    }

    @Autowired
    private GroupRepository repository;

    @Autowired
    private GroupService service;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    Group group = new Group("Prova", "Descrizione");
    Post post = new Post(PostType.REPORT, "6138c1ae94cc8d093a86ef4f", "Some content");

    @Test
    public void testCreate() {
        Group createdGr = service.create(group);

        Optional<Group> result = repository.findById(createdGr.getId());

        assertThat(result).isPresent();
        assertThat(result.orElse(null).getName()).isEqualTo(group.getName());
        assertThat(result.orElse(null).getDescription()).isEqualTo(group.getDescription());
    }

    @Test
    public void testRead() {
        Group g = new Group();
        g.setDescription("Prova");
        g.setName("Riciclo");
        g.setAdmin("user-1");

        Group result = service.read(repository.save(g).getId()).get();

        assertEquals("Riciclo", result.getName());
        assertEquals("Prova", result.getDescription());
    }

    @Test
    public void testUpdate() {
        Group createdGroup = repository.save(group);

        assertEquals("Prova", createdGroup.getName());
        assertEquals("Descrizione", createdGroup.getDescription());

        createdGroup.setDescription("Descrizione aggiornata");
        Group updatedGroup = service.update(createdGroup).get();

        assertEquals("Descrizione aggiornata", updatedGroup.getDescription());
    }

    @Test
    public void testDelete() {
        Group createdGroup = repository.save(group);

        assertEquals(1, repository.findAll().size());
        Optional<Group> findById = repository.findById(createdGroup.getId());

        assertThat(findById).isPresent();
        service.delete(createdGroup.getId());
        Optional<Group> readSug = repository.findById(createdGroup.getId());
        assertThat(readSug).isNotPresent();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testFindByGroup() {
        Group createdGroup = repository.save(group);

        post.setGroup(createdGroup.getId());

        Post createdPost = postRepository.save(post);

        Post secondPost = new Post(PostType.ANNOUNCEMENT, "InvalidGroup", "Should not be there");
        Post secondCreatedPost = postRepository.save(secondPost);

        List<Post> posts = postRepository.findAllByGroup(createdGroup.getId());

        assertThat(posts).contains(createdPost);
        assertThat(posts).doesNotContain(secondCreatedPost);

        assertEquals(1, posts.size());
    }
}
