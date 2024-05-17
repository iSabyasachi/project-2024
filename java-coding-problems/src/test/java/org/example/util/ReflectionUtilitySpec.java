package org.example.util;

import jdk.jfr.Description;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.example.util.ReflectionUtility.getFieldByName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionUtilitySpec {
    @Description("Test Get Field By Name using Reflection")
    @Test
    void test_getFieldByName(){
        Car car = new Car("Honda-CRV", "Hybrid", 220);

        Function<Car, Integer> actualHorsePowerFunc = getFieldByName(Car.class, "horsePower");

        assertEquals(220, actualHorsePowerFunc.apply(car));
    }
}
