package com.larus.itiszuccante.domain;

import java.util.HashMap;
import java.util.Map;

public class FuelConsumption {

    public static Map<FuelType, Map<CarType, Double>> MAP = new HashMap<>();

    static {

        MAP.put(FuelType.BIODIESEL, Map.of(
            CarType.SMALL, 5.23,
            CarType.MID_SIZE, 6.7,
            CarType.LUXURY_SUV_VAN, 8.39)
        );

        MAP.put(FuelType.BIOGAS, Map.of(
            CarType.SMALL, 4.94,
            CarType.MID_SIZE, 6.08,
            CarType.LUXURY_SUV_VAN, 7.22)
        );

        MAP.put(FuelType.DIESEL, Map.of(
            CarType.SMALL, 5.23,
            CarType.MID_SIZE, 6.7,
            CarType.LUXURY_SUV_VAN, 8.39)
        );

        MAP.put(FuelType.ETHANOL_E10, Map.of(
            CarType.SMALL, 6.83,
            CarType.MID_SIZE, 8.42,
            CarType.LUXURY_SUV_VAN, 10.01)
        );

        MAP.put(FuelType.ETHANOL_E85, Map.of(
            CarType.SMALL, 6.83,
            CarType.MID_SIZE, 8.42,
            CarType.LUXURY_SUV_VAN, 10.01)
        );

        MAP.put(FuelType.ELECTRIC, Map.of(
            CarType.SMALL, 16.14,
            CarType.MID_SIZE, 19.9,
            CarType.LUXURY_SUV_VAN, 23.65)
        );

        MAP.put(FuelType.HYBRID, Map.of(
            CarType.SMALL, 4.22,
            CarType.MID_SIZE, 5.2,
            CarType.LUXURY_SUV_VAN, 6.18)
        );

        MAP.put(FuelType.NATURAL_GAS, Map.of(
            CarType.SMALL, 4.94,
            CarType.MID_SIZE, 6.08,
            CarType.LUXURY_SUV_VAN, 7.22)
        );

        MAP.put(FuelType.PETROL, Map.of(
            CarType.SMALL, 6.83,
            CarType.MID_SIZE, 8.42,
            CarType.LUXURY_SUV_VAN, 10.01)
        );

        MAP.put(FuelType.PLUG_IN_HYBRID, Map.of(
            CarType.SMALL, 1.48,
            CarType.MID_SIZE, 1.82,
            CarType.LUXURY_SUV_VAN, 2.16)
        );
    }
}
