package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Behaviour;

import java.util.Optional;

public interface BehaviourService {

    Behaviour create(Behaviour s);
    Optional<Behaviour> read(String id);
    Behaviour update(Behaviour s);
    void delete(String id);

}
