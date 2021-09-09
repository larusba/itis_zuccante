package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Profile
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "profile")
public class Profile extends AbstractAuditingEntity implements Serializable {

    @Id
    private String id;

    @Field("personal_footprint")
    private PersonalFootprint personalFootprint;

    @Field("personal_vehicle")
    private Vehicle vehicle;

    @Field("personal_recycling")
    private Recycling recycling;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalFootprint getPersonalFootprint() {
        return personalFootprint;
    }

    public void setPersonalFootprint(PersonalFootprint personalFootprint) {
        this.personalFootprint = personalFootprint;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Recycling getRecycling() {
        return recycling;
    }

    public void setRecycling(Recycling recycling) {
        this.recycling = recycling;
    }

    @Override
    public String toString() {
        return "Profile{" +
            "id='" + id + '\'' +
            ", personalFootprint=" + personalFootprint +
            ", vehicle=" + vehicle +
            ", recycling=" + recycling +
            '}';
    }
}
