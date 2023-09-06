package it.zuccante.stage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// L'ospedale
@Node("Ospedale")
public class Hospital {

    @Id
    @Property("id")
    private Long id;

    @Size(max = 100)
    @Property("nome")
    private String name;

    @Property("latitudine")
    private Double latitude;

    @Property("longitudine")
    private Double longitude;

    @Size(max = 200)
    @Property("indirizzo")
    private String address;

    @JsonIgnore
    @Relationship(type = "HA_UNITA_OPERATIVA", direction = Relationship.Direction.OUTGOING)
    private Set<Ward>  wards = new HashSet<>();

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        Hospital hospital = (Hospital) o;
        return Objects.equals(id, hospital.id) && Objects.equals(name, hospital.name) && Objects.equals(latitude, hospital.latitude) && Objects.equals(longitude, hospital.longitude) && Objects.equals(address, hospital.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, address);
    }

    @Override
    public String toString() {
        return "Hospital{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", address='" + address + '\'' +
            ", wards=" + wards +
            '}';
    }
}
