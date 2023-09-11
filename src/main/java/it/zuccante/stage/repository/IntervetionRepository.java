package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Intervetion;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface IntervetionRepository extends Neo4jRepository<Intervetion, String> {


   @Query(
    "MATCH (p:Ospedale) WHERE p.nome = $ospedale\n" +
    "MATCH (c:Prestazione) WHERE c.nome = $prestazione\n" +
    "CREATE (uo:Intervento {nome:$nomePaziente, cognome:$cognomePaziente, ambulanza:$numeroAmbulanza, luogo:$luogoIntervento, latitudine:$latitudine, longitudine:$longitudine})\n" +
    "CREATE (p)<-[:SVOLTO {percorrenza:$tempoPercorrenza}]-(uo)-[:RICHIEDE]->(c)\n" +
    "RETURN uo")
    Intervetion createIntervetion(@Param("ospedale")String ospedale, @Param("prestazione")String prestazione,
                                  @Param("nomePaziente")String nomePaziente, @Param("cognomePaziente")String cognomePaziente,
                                  @Param("numeroAmbulanza")String numeroAmbulanza, @Param("luogoIntervento")String luogoIntervento,
                                  @Param("latitudine")double latitudine, @Param("longitudine")double longitudine, @Param("tempoPercorrenza")long tempoPercorrenza);

}

