package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.service.CommentService;
import com.larus.itiszuccante.service.GroupService;
import com.larus.itiszuccante.service.PostService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/groups")
public class GroupResource {

    @Autowired
    private GroupService service;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Group create(@RequestBody Group g) {
        return service.create(g);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> read(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(service.read(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Group> update(@RequestBody Group g, @PathVariable String id) {
        g.setId(id);
        return ResponseUtil.wrapOrNotFound(service.update(g));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
