package com.larus.itiszuccante.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "personal_footprint")
public class PersonalFootprint extends AbstractAuditingEntity implements Serializable {

    @Field("mobility_vehicles")
    private MobilityVehicles mobilityVehicles;

    @Field("mobility_flight")
    private MobilityFlight mobilityFlight;

    @Field("consumption_food")
    private FoodConsumption consumptionFood;

    @Field("consumption_shopping")
    private ShoppingConsumption consumptionShopping;

    @Field("household_area")
    private HouseholdArea householdArea;

    @Field("household_building")
    private HouseholdBuilding householdBuilding;

    @Field("household_heating")
    private HouseholdHeating householdHeating;

    public MobilityVehicles getMobilityVehicles() {
		return mobilityVehicles;
	}

	public void setMobilityVehicles(MobilityVehicles mobilityVehicles) {
		this.mobilityVehicles = mobilityVehicles;
	}

	public MobilityFlight getMobilityFlight() {
		return mobilityFlight;
	}

	public void setMobilityFlight(MobilityFlight mobilityFlight) {
		this.mobilityFlight = mobilityFlight;
	}

	public FoodConsumption getConsumptionFood() {
		return consumptionFood;
	}

	public void setConsumptionFood(FoodConsumption consumptionFood) {
		this.consumptionFood = consumptionFood;
	}

	public ShoppingConsumption getConsumptionShopping() {
		return consumptionShopping;
	}

	public void setConsumptionShopping(ShoppingConsumption consumptionShopping) {
		this.consumptionShopping = consumptionShopping;
	}

	public HouseholdArea getHouseholdArea() {
		return householdArea;
	}

	public void setHouseholdArea(HouseholdArea householdArea) {
		this.householdArea = householdArea;
	}

	public HouseholdBuilding getHouseholdBuilding() {
		return householdBuilding;
	}

	public void setHouseholdBuilding(HouseholdBuilding householdBuilding) {
		this.householdBuilding = householdBuilding;
	}

	public HouseholdHeating getHouseholdHeating() {
		return householdHeating;
	}

	public void setHouseholdHeating(HouseholdHeating householdHeating) {
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
