package it.zuccante.stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.*;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Il reparto dell'ospedale
@Node("UnitaOperativa")
public class Ward {

    @Id
    @GeneratedValue
    //@Property("id")
    private Long id;

    @Size(max = 100)
    @Property("nome")
    private String name;

    @JsonIgnore
    @Relationship(type = "EROGA", direction = Relationship.Direction.OUTGOING)
    private Set<HealthService> healthServices = new HashSet<>();

    @JsonIgnore
    @Relationship(type = "HA_UNITA_OPERATIVA", direction = Relationship.Direction.INCOMING)
    private Set<Hospital> hospitals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HealthService> getHealthServices() {
        return healthServices;
    }

    public void setHealthServices(Set<HealthService> healthServices) {
        this.healthServices = healthServices;
    }

    public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return Objects.equals(id, ward.id) && Objects.equals(name, ward.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Ward{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", healthServices=" + healthServices +
            ", hospitals=" + hospitals +
            '}';
    }
}
