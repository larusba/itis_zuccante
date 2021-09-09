package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "vehicle")
public class Vehicle extends AbstractAuditingEntity implements Serializable {

    @Field("car_type")
    private CarType carType;

    @Field("fuel_type")
    private FuelType fuelType;

    @Field("fuel_consumption")
    private int fuelConsumption;

    // GETTERS AND SETTERS

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
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
