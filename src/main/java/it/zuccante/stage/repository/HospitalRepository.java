package it.zuccante.stage.repository;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.service.dto.TrackDTO;
import org.neo4j.driver.internal.value.MapValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends Neo4jRepository<Hospital, Long> {


    @Query("MATCH (p:Ospedale) RETURN DISTINCT p\n" )
    List<Hospital> findAll();

    @Query("MATCH (o:Ospedale)<-[s:SVOLTO]-(i:Intervento)\n" +
           "WITH o, COUNT(i) as interventi\n" +
            "RETURN {ospedale: o, interventi: interventi}")
    List<MapValue>FindNumberOfInterventionByHospitalAll();


    Optional<Hospital> findOneByName(String name);

    @Query("MATCH (p:Prestazione)\n" +
        "WHERE p.nome IN $healthServices\n" +
        "WITH p\n" +
        "MATCH (p)<-[:EROGA]-(:UnitaOperativa)<-[:HA_UNITA_OPERATIVA]-(o:Ospedale)\n" +
        "RETURN DISTINCT o")
    List<Hospital> findHospitalsByHealthServices(@Param("healthServices") List<String> healthServices);

    @Query("MATCH (p:Prestazione)\n" +
        "WHERE p.nome IN $healthServices\n" +
        "WITH p\n" +
        "MATCH (p)<-[:EROGA]-(:UnitaOperativa)<-[:HA_UNITA_OPERATIVA]-(o:Ospedale)\n" +
        "WITH DISTINCT o.nome as ospedale, o.latitudine + \",\" + o.longitudine as arrivo\n" +
        "CALL apoc.load.json('https://dev.virtualearth.net/REST/V1/Routes/Driving?o=json&wp.0=' + $latitude + ',' + $longitude + '&wp.1=' + arrivo + '&avoid=minimizeTolls&optimize=timeAvoidClosure,timeWithTraffic&key=AgwssaPczXIe2FEQ1cUXtnLVv0Juo3nEfL0OqKVpudIaFdZNqxPK5Wf7wMPdPN3P') YIELD value\n" +
        "WITH ospedale, value.resourceSets as resourceSets\n" +
        "UNWIND resourceSets AS resourceSet\n" +
        "WITH ospedale, resourceSet.resources as resources\n" +
        "UNWIND resources AS resource\n" +
        "WITH  ospedale as hospital , resource.travelDuration as duration , resource.travelDistance as distance , resource.trafficCongestion as congestion\n" +
        "RETURN {hospital:hospital, duration:duration, distance:distance, congestion:congestion } as risultato")
    List<MapValue> findHospitalByShortestDistance(@Param("healthServices") List<String> healthServices, @Param("latitude")double latitude, @Param("longitude")double longitude);
}
