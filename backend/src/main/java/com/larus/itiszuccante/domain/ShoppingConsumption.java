package com.larus.itiszuccante.domain;

public enum ShoppingConsumption {
	
	LOW("below average"), MEDIUM("slightly below average"), HIGH("average"), GIANT("slightly above average");
	private final String description;
	
	private ShoppingConsumption(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}