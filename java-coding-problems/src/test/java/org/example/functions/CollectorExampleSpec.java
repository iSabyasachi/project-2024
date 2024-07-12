package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.people.Person;
import org.example.domain.vehicle.Car;
import org.example.domain.vehicle.Submersible;
import org.example.domain.vehicle.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.example.functions.CollectorExample.*;
import static org.junit.jupiter.api.Assertions.*;

/*
 static <T, R> Collector<T, R, R> of(Supplier<R> supplier, Accumulator<R, T> accumulator, BinaryOperator<R> combiner,
  Collector.Characteristics... characteristics)

 static of(Supplier<R> supplier, Accumulator<R, T> accumulator, BinaryOperator<R> combiner, Function<R> finisher,
  Collector.Characteristics... characteristics)
* */
public class CollectorExampleSpec {
    Map<Integer, Car> carInventory;
    List<Car> cars;
    List<Vehicle> vehicles;

    @BeforeEach
    void init(){
        carInventory = buildCarMap();
        vehicles = buildVehicle();
        cars = buildCars();
    }

    @Description("Collectors.mapping: Calculate Sum And Average of Numbers")
    @Test
    void test_listOfNamesGroupedByAge(){
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, List.of("Dog", "Cat")),
                new Person("Bob", 25, List.of("Parrot")),
                new Person("Charlie", 30, List.of("Hamster", "Rabbit"))
        );

        Map<Integer, List<String>> actualResult = listOfNamesGroupedByAge(people);
        System.out.println(actualResult);

        assertEquals(List.of("Alice", "Charlie"), actualResult.get(30));
        assertEquals(List.of("Bob"), actualResult.get(25));
    }

    @Description("Collectors.teeing: Calculate Sum And Average of Numbers")
    @Test
    void test_calculateSumAndAverageOfNumbers(){
        Map<String, Double> actualResult = calculateSumAndAverageOfNumbers(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertEquals(55, actualResult.get("Sum"));
        assertEquals(5.5, actualResult.get("Average"));
    }

    @Description("Collectors.filtering: Group By Fuel Type And Exclude Hybrid")
    @Test
    void test_groupByFuelTypeAndExcludeHybrid(){
        Map<String, List<Car>> actualResult = groupByFuelTypeAndExcludeHybrid(cars);
        System.out.println(actualResult);

        assertEquals(4, actualResult.size());
        assertEquals(0, actualResult.get("Hybrid").size());
    }

    @Description("Collectors.flatMapping: Group Pets By Owner Name")
    @Test
    void test_groupPetsByOwnerName(){
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, List.of("Dog", "Cat")),
                new Person("Bob", 25, List.of("Parrot")),
                new Person("Charlie", 30, List.of("Hamster", "Rabbit"))
        );

        Map<String, List<String>> actualResult = groupPetsByOwnerName(people);
        System.out.println(actualResult);

        assertEquals(3, actualResult.size());
    }

    @Description("Collectors.flatMapping: Group Pets By Owner Name - Exclude Hamster")
    @Test
    void test_groupPetsByOwnerNameExcludeHamster(){
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, List.of("Dog", "Cat")),
                new Person("Bob", 25, List.of("Parrot")),
                new Person("Charlie", 30, List.of("Hamster", "Rabbit"))
        );

        Map<String, List<String>> actualResult = groupPetsByOwnerNameExcludeHamster(people);
        System.out.println(actualResult);

        assertEquals(3, actualResult.size());
    }


    @Description("MyCollectors.toUnmodifiableListKeep: Find first five Cars using custom collector")
    @Test
    void test_findFirstNCarsUsingCustomCollector(){
        List<Vehicle> actualResult = findFirstNCarsUsingCustomCollector(vehicles, 2);
        System.out.println(actualResult);

        assertEquals(2, actualResult.size());
    }

    @Description("MyCollectors.toUnmodifiableListSkip: Skip first five Cars using custom collector")
    @Test
    void test_skipFirstNCarsUsingCustomCollector(){
        List<Vehicle> actualResult = skipFirstNCarsUsingCustomCollector(vehicles, 2);
        System.out.println(actualResult);

        assertEquals(4, actualResult.size());
    }

    @Description("MyCollectors.toUnmodifiableListSkipUsingCustomSupplier: Skip first five Cars using custom collector and using custom supplier")
    @Test
    void test_skipFirstNCarsUsingCustomCollectorAndCustomSupplier(){
        List<Vehicle> actualResult = skipFirstNCarsUsingCustomCollectorAndCustomSupplier(vehicles, 2);
        System.out.println(actualResult);

        assertEquals(4, actualResult.size());
    }

    @Description("MyCollectors.toUnmodifiableListKeepRange: Keep range of Cars using custom collector and using custom supplier")
    @Test
    void test_findRangeOfCarsUsingCustomCollectorAndCustomSupplier(){
        List<Vehicle> actualResult = findRangeOfCarsUsingCustomCollectorAndCustomSupplier(vehicles, 2, 4);
        System.out.println(actualResult);

        assertEquals(2, actualResult.size());
    }

    @Description("limit: Find first five Cars using limit")
    @Test
    void test_findFirstNVehicleUsingLimit(){
        assertEquals(5, findFirstNCarsUsingLimit(vehicles, 5).size());
    }

    @Description("skip: Eliminate first five Cars using skip")
    @Test
    void test_eliminateFirstNVehicleUsingSkip(){
        assertEquals(4, eliminateFirstNCarsUsingSkip(vehicles, 2).size());
    }

    @Description("Collector.of: Collect Sum of numbers using Collector")
    @Test
    void test_summingIntExample(){
        assertEquals(15, summingIntExample(List.of(1, 2, 3, 4, 5)));
    }

    @Description("MyCollectors.toTreeSet: Writing a custom collector that collects into a TreeSet")
    @Test
    void test_collectIntoTreeSet(){
        TreeSet<String> actualResult = collectCarsAndReturnTreeSet(carInventory);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("MyCollectors.toTreeSet: Writing a custom collector that collects sorted HP GT 100 into a TreeSet")
    @Test
    void test_collectSortedHPGT100andReturnTreeSet(){
        TreeSet<Integer> actualResult = collectSortedHPGT100andReturnTreeSet(carInventory);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("MyCollectors.toLinkedHashSet: Writing a custom collector that collects Ordered HP GT 100 into a TreeSet")
    @Test
    void test_collectOrderedHPGT100andReturnLinkedHashSet(){
        LinkedHashSet<Integer> actualResult = collectOrderedHPGT100andReturnLinkedHashSet(carInventory);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("MyCollectors.exclude: Writing a custom collector that excludes elements of another collector")
    @Test
    void test_excludesElementsFromAnotherCollector(){
        LinkedHashSet<Integer> actualResult = excludesElementsFromAnotherCollector(carInventory);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("MyCollectors.toType: Writing a custom collector that collects elements by type - Only Car")
    @Test
    void test_collectsElementsByTypeOnlyCar(){
        List<Car> actualResult = collectsElementsByTypeOnlyCar(vehicles);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("MyCollectors.toType: Writing a custom collector that collects elements by type - Only Submersible")
    @Test
    void test_collectsElementsByTypeOnlySubmersible(){
        HashSet<Submersible> actualResult = collectsElementsByTypeOnlySubmersible(vehicles);

        System.out.println(actualResult);

        assertFalse(actualResult.isEmpty());
    }

    @Description("Writing a custom collector for SplayTree. Once Chapter 5, Solve this after Problem 127 solved")
    @Test
    @Disabled
    void test_customCollectorForSplayTree(){
        boolean actualResult = customCollectorForSplayTree();

        assertTrue(actualResult);
    }

    private Map<Integer, Car> buildCarMap() {
        return Map.of(
                1, new Car("Honda-CRV", "Electric", 220),
                2, new Car("Porsha", "Gas", 500),
                3, new Car("Toyota-Camry", "Gas", 170),
                4, new Car("Hyundai-Venue", "Petrol", 150),
                5, new Car("Toyota-Rav4", "Electric", 225),
                6, new Car("Tesla", "Electric", 201),
                7, new Car("Honda-Civic", "Gas", 165)
        );
    }

    private List<Car> buildCars() {
        return List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Porsha", "Gas", 500),
                new Car("Toyota-Camry", "Gas", 170),
                new Car("Hyundai-Venue", "Petrol", 150),
                new Car("Toyota-Rav4", "Electric", 225),
                new Car("Tesla", "Electric", 201),
                new Car("Honda-Civic", "Gas", 165)
        );
    }

    private List<Vehicle> buildVehicle() {
        Vehicle mazda = new Car("Mazda", "diesel", 155);
        Vehicle ferrari = new Car("Ferrari", "gasoline", 500);
        Vehicle honda = new Car("Honda", "gasoline", 200);
        Vehicle toyota = new Car("Toyota", "hybrid", 200);

        Vehicle hov = new Submersible("HOV", 3000);
        Vehicle rov = new Submersible("ROV", 7000);

        return List.of(mazda, hov, honda, ferrari, rov, toyota);
    }

    @AfterEach
    void destroy(){
        carInventory = null;
        vehicles = null;
    }
}
