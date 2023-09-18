package it.zuccante.stage.service.dto;

import java.io.Serializable;
import java.util.List;

public class InterventionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomePaziente;
    private String cognomePaziente;
    private String numeroAmbulanza;
    private String luogoIntervento;
    private double latitude;
    private double longitude;

    private double tempoPercorrenza;
    private String nomeOspedale;
    private List<String> nomePrestazione;

    public String getNomePaziente() {
        return nomePaziente;
    }

    public void setNomePaziente(String nomePaziente) {
        this.nomePaziente = nomePaziente;
    }

    public String getCognomePaziente() {
        return cognomePaziente;
    }

    public void setCognomePaziente(String cognomePaziente) {
        this.cognomePaziente = cognomePaziente;
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

    public double getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(int tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public String getNomeOspedale() {
        return nomeOspedale;
    }

    public void setNomeOspedale(String nomeOspedale) {
        this.nomeOspedale = nomeOspedale;
    }

    public List<String> getNomePrestazione() {
        return nomePrestazione;
    }

    public void setNomePrestazione(List<String> nomePrestazione) {
        this.nomePrestazione = nomePrestazione;
    }


}
