package it.zuccante.stage.service.dto;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;

public class IntervetionDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String cognome;
    private String numeroAmbulanza;
    private String luogoIntervento;
    private double latitude;
    private double longitude;
    private int tempoPercorrenza;
    private String nomeHospedale;
    private String nomePrestazione;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumeroAmbulanza() {
        return numeroAmbulanza;
    }

    public void setNumeroAmbulanza(String numeroAmbulanza) {
        this.numeroAmbulanza = numeroAmbulanza;
    }

    public String getLuogoIntervento() {
        return luogoIntervento;
    }

    public void setLuogoIntervento(String luogoIntervento) {
        this.luogoIntervento = luogoIntervento;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(int tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public String getNomeHospedale() {
        return nomeHospedale;
    }

    public void setNomeHospedale(String nomeHospedale) {
        this.nomeHospedale = nomeHospedale;
    }

    public String getNomePrestazione() {
        return nomePrestazione;
    }

    public void setNomePrestazione(String nomePrestazione) {
        this.nomePrestazione = nomePrestazione;
    }


}
