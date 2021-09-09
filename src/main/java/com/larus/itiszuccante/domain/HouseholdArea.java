package com.larus.itiszuccante.domain;

public enum HouseholdArea {
	
	LOW("small"), HIGH("large");
	private final String description;
	
	private HouseholdArea(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}