package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "vehicle")
public class Vehicle extends AbstractAuditingEntity implements Serializable {

    @Field("car_type")
    private String carType;

    @Field("fuel_type")
    private String fuelType;

    @Field("fuel_consumption")
    private int fuelConsumption;

    // GETTERS AND SETTERS

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "carType='" + carType + '\'' +
            ", fuelType='" + fuelType + '\'' +
            ", fuelConsumption=" + fuelConsumption +
            '}';
    }
}
