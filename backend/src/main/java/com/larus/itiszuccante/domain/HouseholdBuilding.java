package com.larus.itiszuccante.domain;

public enum HouseholdBuilding {
	
	LOW("energy efficient"), HIGH("old");
	private final String description;
	
	private HouseholdBuilding(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}