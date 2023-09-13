package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Intervention;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InterventionRepository extends Neo4jRepository<Intervention, String> {


   @Query(
    "MATCH (o:Ospedale)-[:HA_UNITA_OPERATIVA]->(uo:UnitaOperativa)-[:EROGA]->(p:Prestazione) WHERE o.nome = $ospedale AND p.nome = $prestazione\n" +
    "CREATE (i:Intervento {nome:$nomePaziente, cognome:$cognomePaziente, ambulanza:$numeroAmbulanza, luogo:$luogoIntervento, latitudine:$latitudine, longitudine:$longitudine})\n" +
    "CREATE (o)<-[:SVOLTO {percorrenza:$tempoPercorrenza}]-(i)-[:RICHIEDE]->(p)\n" +
    "RETURN i")
   Optional<Intervention> createIntervetion(@Param("ospedale")String ospedale, @Param("prestazione")String prestazione,
                                            @Param("nomePaziente")String nomePaziente, @Param("cognomePaziente")String cognomePaziente,
                                            @Param("numeroAmbulanza")String numeroAmbulanza, @Param("luogoIntervento")String luogoIntervento,
                                            @Param("latitudine")double latitudine, @Param("longitudine")double longitudine, @Param("tempoPercorrenza")double tempoPercorrenza);

}

