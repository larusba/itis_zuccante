package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Behaviour;

public interface BehaviourService {

    Behaviour create(Behaviour s);
    Behaviour read(String id);
    Behaviour update(Behaviour s);
    void delete(String id);

}
