package com.larus.itiszuccante.domain;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location")
public class Location extends AbstractAuditingEntity implements Serializable {
	
	private int lat;
	private int lon;

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lon=" + lon + "]";
	}

}