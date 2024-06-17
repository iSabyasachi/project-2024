package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.functions.ContainsAllOrAnyExample.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContainsAllOrAnyExampleSpec {
    List<Car> carInventoryList;
    @BeforeEach
    void init(){
        carInventoryList = buildCar();
    }

    @AfterEach
    void destroy(){
        carInventoryList = null;
    }

    @Description("Test ContainsAll via extension of Stream")
    @Test
    void test_checkIfContainsAllCarsUsingExtensionOfStream_shouldReturnFalse(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-HRV", "Gas", 198)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-HRV", "Gas", 198));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));

        assertFalse(actualResultForInputList);
        assertFalse(actualResultForInputArray);
        assertFalse(actualResultForInputItem);
    }

    @Description("Test ContainsAll via extension of Stream")
    @Test
    void test_checkIfContainsAllCarsUsingExtensionOfStream_shouldReturnTrue(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));

        assertTrue(actualResultForInputList);
        assertTrue(actualResultForInputArray);
        assertTrue(actualResultForInputItem);
    }

    @Description("Test ContainsAny via extension of Stream")
    @Test
    void test_checkIfContainsAnyCarsUsingExtensionOfStream_shouldReturnFalse(){
        boolean actualResultForInputList = checkIfContainsAnyCarsUsingExtensionOfStream(carInventoryList, List.of(
                new Car("Honda-HRV", "Gas", 198)));
        boolean actualResultForInputArray = checkIfContainsAnyCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));
        boolean actualResultForInputItem = checkIfContainsAnyCarsUsingExtensionOfStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));

        assertFalse(actualResultForInputList);
        assertFalse(actualResultForInputArray);
        assertFalse(actualResultForInputItem);
    }

    @Description("Test ContainsAny via extension of Stream")
    @Test
    void test_checkIfContainsAnyCarsUsingExtensionOfStream_shouldReturnTrue(){
        boolean actualResultForInputList = checkIfContainsAnyCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220)));
        boolean actualResultForInputArray = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));
        boolean actualResultForInputItem = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));

        assertTrue(actualResultForInputList);
        assertTrue(actualResultForInputArray);
        assertTrue(actualResultForInputItem);
    }

    @Description("Test ContainsAny via Custom Stream")
    @Test
    void test_checkIfContainsAnyCarsUsingCustomStream_shouldReturnFalse(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-HRV", "Gas", 198)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));

        assertFalse(actualResultForInputList);
        assertFalse(actualResultForInputArray);
        assertFalse(actualResultForInputItem);
    }

    @Description("Test ContainsAny via Custom Stream")
    @Test
    void test_checkIfContainsAnyCarsUsingCustomStream_shouldReturnTrue(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));

        assertTrue(actualResultForInputList);
        assertTrue(actualResultForInputArray);
        assertTrue(actualResultForInputItem);
    }

    @Description("Test ContainsAll via Custom Stream")
    @Test
    void test_checkIfContainsAllCarsUsingCustomStream_shouldReturnFalse(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-HRV", "Gas", 198)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-HRV", "Gas", 198));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));

        assertFalse(actualResultForInputList);
        assertFalse(actualResultForInputArray);
        assertFalse(actualResultForInputItem);
    }

    @Description("Test ContainsAll via Custom Stream")
    @Test
    void test_checkIfContainsAllCarsUsingCustomStream_shouldReturnTrue(){
        boolean actualResultForInputList = checkIfContainsAllCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220)));
        boolean actualResultForInputArray = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));
        boolean actualResultForInputItem = checkIfContainsAllCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));

        assertTrue(actualResultForInputList);
        assertTrue(actualResultForInputArray);
        assertTrue(actualResultForInputItem);
    }

    @Description("Test ContainsAny via Custom Stream")
    @Test
    void test_checkIfContainsAnyCars_shouldReturnFalse(){
        boolean actualResultForInputList = checkIfContainsAnyCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-HRV", "Gas", 198)));
        boolean actualResultForInputArray = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));
        boolean actualResultForInputItem = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-HRV", "Gas", 198));

        assertFalse(actualResultForInputList);
        assertFalse(actualResultForInputArray);
        assertFalse(actualResultForInputItem);
    }

    @Description("Test ContainsAny via Custom Stream")
    @Test
    void test_checkIfContainsAnyCars_shouldReturnTrue(){
        boolean actualResultForInputList = checkIfContainsAnyCarsUsingCustomStream(carInventoryList, List.of(
                new Car("Honda-CRV", "Hybrid", 220)));
        boolean actualResultForInputArray = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));
        boolean actualResultForInputItem = checkIfContainsAnyCarsUsingCustomStream(carInventoryList,
                new Car("Honda-CRV", "Hybrid", 220));

        assertTrue(actualResultForInputList);
        assertTrue(actualResultForInputArray);
        assertTrue(actualResultForInputItem);
    }

    public List<Car> buildCar(){
        return List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Porsha", "Gas", 500),
                new Car("Toyota-Camry", "Gas", 170),
                new Car("Hyundai-Venue", "Petrol", 150),
                new Car("Toyota-Rav4", "Hybrid", 225),
                new Car("Honda-Accord", "Hybrid", 201),
                new Car("Honda-Civic", "Gas", 165)
        );
    }
}
