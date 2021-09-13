package com.larus.itiszuccante.domain;

public enum MobilityVehicles {
	
	LOW("occasionally use transport"), MEDIUM("predominantly use transport"), HIGH("usually drive"), GIANT("mainly drive");
	private final String description;
	
	private MobilityVehicles(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}