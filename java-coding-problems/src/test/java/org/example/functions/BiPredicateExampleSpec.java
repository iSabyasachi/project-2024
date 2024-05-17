package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import static org.example.functions.BiPredicateExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiPredicateExampleSpec {
    List<Car> cars;
    @BeforeEach
    void init(){
        cars = buildCar();
    }

    @Description("Test Using BiPredicate: Get List of Car by Filter Map")
    @Test
    void test_getListOfCarsByFilterMap(){
        List<Car> expectedCars = List.of(
                new Car("Honda-CRV", "Hybrid", 220)
        );

        Map<String, Object> filterMap = Map.of(
                "brand", "Honda",
                "fuel", "Hybrid",
                "horsePower", 210
        );

        Map<String, String> filterCriteria = Map.of(
                "brand", "STARTS_WITH",
                "fuel", "EQUALS",
                "horsePower", "GE"
        );


        List<Car> actualCars = getListOfCarsByFilterMap(cars, filterMap, filterCriteria);

        assertEquals(expectedCars, actualCars);
    }

    @Description("Test Using BiPredicate: Get List of Car start with Brand Name")
    @Test
    void test_getListOfCarsStartsWithBrandName(){
        List<Car> expectedCars = List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-Accord", "Hybrid", 201),
                new Car("Honda-Civic", "Gas", 165)
        );

        List<Car> actualCars = getListOfCarsStartsWithBrandName(cars, "Honda");

        assertEquals(expectedCars, actualCars);
    }

    @Description("Test Using BiPredicate: Get List of Car start with Brand Name and Fuel Type")
    @Test
    void test_getListOfCarsStartsWithBrandNameAndFuelType(){
        List<Car> expectedCars = List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Honda-Accord", "Hybrid", 201)
        );

        List<Car> actualCars = getListOfCarsStartsWithBrandNameAndFuelType(cars, "Honda", "Hybrid");

        assertEquals(expectedCars, actualCars);
    }

    @Description("Test Using BiPredicate: Get List Of Cars whose HorsePower GT the Search Value")
    @Test
    void test_getListOfCarsHorsePowerGT(){
        List<Car> expectedCars = List.of(new Car("Porsha", "Gas", 500));

        List<Car> actualCars = getListOfCarsHorsePowerGT(cars, 300);

        assertEquals(expectedCars, actualCars);
    }

    @Description("Test Using BiPredicate: Get List Of Cars whose HorsePower Between the Search Values")
    @Test
    void test_getListOfCarsHorsePowerBetween(){
        List<Car> expectedCars = List.of(
                new Car("Toyota-Camry", "Gas", 170),
                new Car("Hyundai-Venue", "Petrol", 150),
                new Car("Honda-Civic", "Gas", 165)
        );

        List<Car> actualCars = getListOfCarsHorsePowerBetween(cars, 100, 200);

        assertEquals(expectedCars, actualCars);
    }

    @Description("Test Using BiPredicate: Exemplify the usage of BiPredicate")
    @Test
    void test_usageOfBiPredicate(){
        Car foundCar = new Car("Honda-CRV", "Hybrid", 220);
        //BiPredicate<List<Car>, Car> horsePowerPredicate = (cars, car) -> cars.contains(car);
        BiPredicate<List<Car>, Car> horsePowerBiPredicate = List::contains;

        assertEquals(Boolean.TRUE, horsePowerBiPredicate.test(cars, foundCar));
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

    @AfterEach
    void destory(){
        cars = null;
    }
}
