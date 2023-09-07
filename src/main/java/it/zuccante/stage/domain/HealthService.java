package it.zuccante.stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.*;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("Prestazione")
public class HealthService {

    @Id
    @Size(max = 100)
    @Property("nome")
    private String name;

    @JsonIgnore
    @Relationship(type = "EROGA", direction = Relationship.Direction.INCOMING)
    private Set<Ward> wards = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ward> getWards() {
        return wards;
    }

    public void setWards(Set<Ward> wards) {
        this.wards = wards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthService that = (HealthService) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "HealthService{" +
            "name='" + name + '\'' +
            '}';
    }
}
