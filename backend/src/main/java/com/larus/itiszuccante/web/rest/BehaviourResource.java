package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/{userId}/behaviours")
public class BehaviourResource {

    @Autowired
    private BehaviourService service;

    @PostMapping
    public ResponseEntity<Behaviour> create(@PathVariable String userId, @RequestBody Behaviour b) {
        return new ResponseEntity<Behaviour>(service.create(userId, b), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Behaviour> read(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(service.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Behaviour> update(@RequestBody Behaviour b, @PathVariable String id) {
        b.setId(id);
        return new ResponseEntity<Behaviour>(service.update(b), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Behaviour> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<Behaviour>(HttpStatus.NO_CONTENT);
    }

}
