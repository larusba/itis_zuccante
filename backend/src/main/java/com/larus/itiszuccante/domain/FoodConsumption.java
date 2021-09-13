package com.larus.itiszuccante.domain;

public enum FoodConsumption {

	LOW("vegan"), MEDIUM("mostly vegetarian"), HIGH("meat every day on average"), GIANT("meat at most every meal");

	private final String description;

	private FoodConsumption(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}