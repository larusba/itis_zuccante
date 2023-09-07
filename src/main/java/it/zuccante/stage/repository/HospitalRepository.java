package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Hospital;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends Neo4jRepository<Hospital, Long> {

    Optional<Hospital> findOneByName(String name);

    @Query("MATCH (p:Prestazione)\n" +
        "WHERE p.nome IN $healthServices\n" +
        "WITH p\n" +
        "MATCH (p)<-[:EROGA]-(:UnitaOperativa)<-[:HA_UNITA_OPERATIVA]-(o:Ospedale)\n" +
        "RETURN DISTINCT ospedale")
    List<Hospital> findHospitalsByHealthServices(List<String> healthServices);
}
