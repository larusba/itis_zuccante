package it.zuccante.stage.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("Intervento")
public class Intervention {
    @Property("nome")
    private String nomePaziente;

    @Property("cognome")
    private String cognomePaziente;

    @Id
    @Property("ambulanza")
    private String numeroAmbulanza;

    @Property("luogo")
    private String luogoIntervento;

    @Property("latitudine")
    private double latitude;

    @Property("longitudine")
    private double longitude;

    @JsonIgnore
    @Relationship(type = "RICHIEDE", direction = Relationship.Direction.OUTGOING)
    private Set<HealthService> healthServices = new HashSet<>();

    @JsonIgnore
    @Relationship(type = "SVOLTO", direction = Relationship.Direction.OUTGOING)
    private Set<Hospital> hospitals = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intervention that = (Intervention) o;
        return Objects.equals(nomePaziente, that.nomePaziente) && Objects.equals(cognomePaziente, that.cognomePaziente) && Objects.equals(numeroAmbulanza, that.numeroAmbulanza) && Objects.equals(luogoIntervento, that.luogoIntervento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePaziente, cognomePaziente, numeroAmbulanza, luogoIntervento);
    }

    @Override
    public String toString() {
        return "Intervation{" +
            "name='" + nomePaziente + '\'' +
            ", cognome='" + cognomePaziente + '\'' +
            ", numeroAmbulanza='" + numeroAmbulanza + '\'' +
            ", luogoIntervento='" + luogoIntervento + '\'' +
            '}';
    }
}
