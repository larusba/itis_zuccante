package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @PostMapping
    public Post create(@RequestBody Post p) {
        return service.create(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> read(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(service.read(id));
    }

    @PatchMapping("/{id}")
    public Post update(@RequestBody Post p, @PathVariable String id) {
        p.setId(id);
        return service.update(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
