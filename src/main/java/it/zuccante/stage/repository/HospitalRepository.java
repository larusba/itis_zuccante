package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Hospital;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends Neo4jRepository<Hospital, Long> {


    @Query("MATCH (p:Ospedale) RETURN DISTINCT p")
    List<Hospital> findAll();

    Optional<Hospital> findOneByName(String name);

    @Query("MATCH (p:Prestazione)\n" +
        "WHERE p.nome IN $healthServices\n" +
        "WITH p\n" +
        "MATCH (p)<-[:EROGA]-(:UnitaOperativa)<-[:HA_UNITA_OPERATIVA]-(o:Ospedale)\n" +
        "RETURN DISTINCT o")
    List<Hospital> findHospitalsByHealthServices(@Param("healthServices") List<String> healthServices);
}
