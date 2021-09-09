package com.larus.itiszuccante.domain;

public enum CarType {

    SMALL(5.23), MID_SIZE(6.7), LUXURY_SUV_VAN(8.39);

    /**
     * Litres consumption for 100km
     */
    private final double consumption;

    CarType(double d) {
        this.consumption = d;
    }

    public double getConsumption() {
        return consumption;
    }

}
