package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Group read(@PathVariable String id) {
        return service.read(id);
    }

    @PutMapping("/{id}")
    public Group update(@RequestBody Group g, @PathVariable String id) {
        g.setId(id);
        return service.update(g);
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
