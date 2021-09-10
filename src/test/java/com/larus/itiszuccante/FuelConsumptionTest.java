package com.larus.itiszuccante;

import com.larus.itiszuccante.domain.CarType;
import com.larus.itiszuccante.domain.FuelConsumption;
import com.larus.itiszuccante.domain.FuelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@IntegrationTest
public class FuelConsumptionTest {

    @Test
    public void test() {
        Assertions.assertEquals(19.9, FuelConsumption.MAP.get(FuelType.ELECTRIC).get(CarType.MID_SIZE));
        Assertions.assertEquals(8.39, FuelConsumption.MAP.get(FuelType.DIESEL).get(CarType.LUXURY_SUV_VAN));
        Assertions.assertEquals(6.83, FuelConsumption.MAP.get(FuelType.PETROL).get(CarType.SMALL));
    }
}
