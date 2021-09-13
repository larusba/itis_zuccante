package com.larus.itiszuccante.domain;

public enum MobilityFlight {
	
	LOW("never fly privately"), MEDIUM("fly privately once or twice within Europe"), HIGH("fly long haul once a year"), GIANT("fly regularly");
	private final String description;
	
	private MobilityFlight(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}