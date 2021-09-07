package com.larus.itiszuccante.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@org.springframework.data.mongodb.core.mapping.Document(collection = "personal_footprint")
public class PersonalFootprint extends AbstractAuditingEntity implements Serializable {

    @Field("mobility_vehicles")
    private String mobilityVehicles;

    @Field("mobility_flight")
    private String mobilityFlight;

    @Field("consumption_food")
    private String consumptionFood;

    @Field("consumption_shopping")
    private String consumptionShopping;

    @Field("household_area")
    private String householdArea;

    @Field("household_building")
    private String householdBuilding;

    @Field("household_heating")
    private String householdHeating;

    public String getMobilityVehicles() {
        return mobilityVehicles;
    }

    public void setMobilityVehicles(String mobilityVehicles) {
        this.mobilityVehicles = mobilityVehicles;
    }

    public String getMobilityFlight() {
        return mobilityFlight;
    }

    public void setMobilityFlight(String mobilityFlight) {
        this.mobilityFlight = mobilityFlight;
    }

    public String getConsumptionFood() {
        return consumptionFood;
    }

    public void setConsumptionFood(String consumptionFood) {
        this.consumptionFood = consumptionFood;
    }

    public String getConsumptionShopping() {
        return consumptionShopping;
    }

    public void setConsumptionShopping(String consumptionShopping) {
        this.consumptionShopping = consumptionShopping;
    }

    public String getHouseholdArea() {
        return householdArea;
    }

    public void setHouseholdArea(String householdArea) {
        this.householdArea = householdArea;
    }

    public String getHouseholdBuilding() {
        return householdBuilding;
    }

    public void setHouseholdBuilding(String householdBuilding) {
        this.householdBuilding = householdBuilding;
    }

    public String getHouseholdHeating() {
        return householdHeating;
    }

    public void setHouseholdHeating(String householdHeating) {
        this.householdHeating = householdHeating;
    }

    @Override
    public String toString() {
        return "PersonalFootprint{" +
            "mobilityVehicles='" + mobilityVehicles + '\'' +
            ", mobilityFlight='" + mobilityFlight + '\'' +
            ", consumptionFood='" + consumptionFood + '\'' +
            ", consumptionShopping='" + consumptionShopping + '\'' +
            ", householdArea='" + householdArea + '\'' +
            ", householdBuilding='" + householdBuilding + '\'' +
            ", householdHeating='" + householdHeating + '\'' +
            '}';
    }
}
