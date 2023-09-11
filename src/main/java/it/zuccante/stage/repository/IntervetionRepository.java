package it.zuccante.stage.repository;

import it.zuccante.stage.domain.Intervetion;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface IntervetionRepository extends Neo4jRepository<Intervetion, String> {


   @Query(
    "MATCH (p:Ospedale) WHERE p.nome = $ospedale\n" +
    "MATCH (c:Prestazione) WHERE c.nome = $prestazione\n" +
    "CREATE (uo:Intervento {nome:$nomePaziente, cognome:$cognomePaziente, ambulanza:$numeroAmbulanza, luogo:$luogoIntervento, latitudine:$latitudine, longitudine:$longitudine})\n" +
    "CREATE (p)<-[:SVOLTO {percorrenza:$tempoPercorrenza}]-(uo)-[:RICHIEDE]->(c)\n")
    void createIntervetion(String ospedale, String prestazione, String nomePaziente, String cognomePaziente, String numeroAmbulanza, String luogoIntervento, long latitudine, long longitudine, long tempoPercorrenza);



}

