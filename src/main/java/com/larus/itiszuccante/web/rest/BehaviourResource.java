package com.larus.itiszuccante.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.service.BehaviourService;

@RestController
@RequestMapping("/api/behaviours")
public class BehaviourResource {

    @Autowired
    private BehaviourService service;

    @PostMapping
    public Behaviour create(@RequestBody Behaviour b) {
        return service.create(b);
    }

    @GetMapping("/{id}")
    public Behaviour read(@PathVariable String id) {
        return service.read(id);
    }

    @PutMapping("/{id}")
    public Behaviour update(@RequestBody Behaviour b, @PathVariable String id) {
        b.setId(id);
        return service.update(b);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
