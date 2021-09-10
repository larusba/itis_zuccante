package com.larus.itiszuccante.domain;

import java.util.Map;

public enum FuelType {

    BIODIESEL(Map.of("default", 0.000038)), 
    BIOGAS(Map.of("default", 0.000048 )),
    DIESEL(Map.of("default", 0.000046)),
    ETHANOL_E10(Map.of("default", 0.000039)),
    ETHANOL_E85(Map.of("default", 0.000023)),
    ELECTRIC(Map.of("AT", 0.000007, "DE", 0.000009, "SE", 0.000004, "CH", 0.000005, "CERTIFIED_GREEN", 0.000004, "REST", 0.000008)),
    HYBRID(Map.of("default", 0.000049)),
    NATURAL_GAS(Map.of("default", 0.000050)),
    PETROL(Map.of("default", 0.000040)),
    PLUG_IN_HYBRID(Map.of("AT", 0.000182, "DE", 0.000222, "SE", 0.000135, "CH", 0.000148, "CERTIFIED_GREEN", 0.000128, "REST", 0.000195));
	private final Map<String, Double> emission;
	
	FuelType(Map<String, Double> emission) {
		this.emission = emission;
	}

	public Map<String, Double> getEmission() {
		return emission;
	}
	
}