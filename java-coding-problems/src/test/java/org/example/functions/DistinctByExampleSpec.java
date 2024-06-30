package org.example.functions;

import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.example.functions.DistinctByExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DistinctByExampleSpec {

    List<Car> cars;
    @BeforeEach
    void init(){
        cars = buildCar();
    }

    @Test
    void test_findListOfCarsDistinctByBrandAndName(){
        List<CarRecord> carRecords = List.of(
                new CarRecord("Honda", "CR-V", "Hybrid", 210),
                new CarRecord("Honda", "CR-V", "Hybrid", 210),
                new CarRecord("Honda", "HR-V", "Hybrid", 110),
                new CarRecord("Toyota", "RAV4", "Gas", 210),
                new CarRecord("Toyota", "Camry", "Gas", 170)
        );

        List<CarRecord> actualResult = findAllCarsDistinctByBrandAndNameUsingMultipleKeys(carRecords);
        System.out.println(actualResult);
    }

    @DisplayName("ConcurrentHashMap And Set : Distinct By Intermediate Operation: Find all the cars distinct by brand.")
    @Test
    void test_findAllCarsDistinctByBrandUsingConcurrentHashMapAndSet() {
        List<Car> cars = List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Porsha", "Gas", 500),
                new Car("Toyota-Camry", "Gas", 170));

        List<Car> actualResult = findAllCarsDistinctByBrandUsingConcurrentHashMapAndSet(cars);
        System.out.println(actualResult);

        //assertEquals(Boolean.FALSE, actualResult.contains(null));
    }

    @DisplayName("ConcurrentHashMap : Distinct By Intermediate Operation: Find all the cars distinct by brand.")
    @Test
    void test_findAllCarsDistinctByBrandUsingConcurrentHashMap() {
        List<Car> actualResult = findAllCarsDistinctByBrandUsingConcurrentHashMap(cars);
        System.out.println(actualResult);

        //assertEquals(Boolean.FALSE, actualResult.contains(null));
    }

    @DisplayName("Helper : Distinct By Intermediate Operation: Find all the cars distinct by brand.")
    @Test
    void test_findAllCarsDistinctByBrandUsingHelper() {
        Map<String, Car> actualResult = findAllCarsDistinctByBrandUsingHelper(cars);
        System.out.println(actualResult);

        assertEquals("Porsha", actualResult.get("Porsha").getBrand());
    }

    @DisplayName("Distinct By Intermediate Operation: Find all the cars distinct by brand.")
    @Test
    void test_findAllCarsDistinctByBrand() {
        Map<String, Car> actualResult = findAllCarsDistinctByBrand(cars);
        System.out.println(actualResult);

        assertEquals("Porsha", actualResult.get("Porsha").getBrand());
    }

    public List<Car> buildCar(){
        return List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Porsha", "Gas", 500),
                new Car("Toyota-Camry", "Gas", 170)
        );
    }

    @AfterEach
    void destroy(){
        cars = null;
    }
}
