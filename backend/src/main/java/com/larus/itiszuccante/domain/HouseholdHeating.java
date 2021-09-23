package com.larus.itiszuccante.domain;

public enum HouseholdHeating {
	
    LOW("renewable energies"), HIGH("fossil fuels");
    private final String description;

    private HouseholdHeating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}