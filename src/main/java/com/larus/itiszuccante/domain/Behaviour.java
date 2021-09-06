package com.larus.itiszuccante.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "behaviour")
public class Behaviour extends AbstractAuditingEntity implements Serializable {
	
	@Id
	private String id;
	
	private String type;
	
	private Date date;
	
	private int distance;
	
	@Field("electric_location")
	private String electricLocation;
	
	private float emission;
	
	@Size(min = 3, max = 3)
	private String from;
	
	@Size(min = 3, max = 3)
	private String to;
	
	@Size(min = 3, max = 3)
	private String via;
	
	private int passengers;
	
	private boolean roundtrip;
	
	@Field("flight_class")
	private String flightClass;
	
	@Field("waste_type")
	private String wasteType;
	
	private int bags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getElectricLocation() {
		return electricLocation;
	}

	public void setElectricLocation(String electricLocation) {
		this.electricLocation = electricLocation;
	}

	public float getEmission() {
		return emission;
	}

	public void setEmission(float emission) {
		this.emission = emission;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public boolean isRoundtrip() {
		return roundtrip;
	}

	public void setRoundtrip(boolean roundtrip) {
		this.roundtrip = roundtrip;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getWasteType() {
		return wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	public int getBags() {
		return bags;
	}

	public void setBags(int bags) {
		this.bags = bags;
	}

	@Override
	public String toString() {
		return "Behaviour [id=" + id + ", type=" + type + ", date=" + date + ", distance=" + distance
				+ ", electricLocation=" + electricLocation + ", emission=" + emission + ", from=" + from + ", to=" + to
				+ ", via=" + via + ", passengers=" + passengers + ", roundtrip=" + roundtrip + ", flightClass="
				+ flightClass + ", wasteType=" + wasteType + ", bags=" + bags + "]";
	}
	
	

}