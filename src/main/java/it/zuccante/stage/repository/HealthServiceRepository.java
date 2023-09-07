package it.zuccante.stage.repository;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.Hospital;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface HealthServiceRepository extends Neo4jRepository<HealthService, String> {
    //List<HealthService> findAll();
    @Query("MATCH (p:UnitaOperativa)\n" +
        "WHERE p.nome IN $healthServices\n" +
        "WITH p\n" +
        "MATCH (p)<-[:EROGA]-(:Prestazione)<-[:EROGA]-(o:UnitaOperativa)\n" +
        "RETURN DISTINCT Prestazione")
    List<HealthService> findHealthServiceByName(List<String> unitaOperativa);
}
