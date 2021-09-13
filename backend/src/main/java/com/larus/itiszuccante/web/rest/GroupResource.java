package com.larus.itiszuccante.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.service.GroupService;

import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/groups")
public class GroupResource {

    @Autowired
    private GroupService service;

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
    /*@GetMapping
    public List<Group> findAllByParticipant(@RequestParam User user) {
        return service.findAllByParticipant(user);
    }*/
}
