package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Intervation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IntervationRepository  extends Neo4jRepository<Intervation, String> {


}

