package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.example.functions.StreamComparatorsExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamComparatorsExampleSpec {
    List<Car> carInventoryList;
    Map<Integer, Car> carInventoryMap;
    @BeforeEach
    void init(){
        carInventoryList = buildCar();
        carInventoryMap = buildCarMap();
    }

    private Map<Integer, Car> buildCarMap() {
        return Map.of(
                1, new Car("Dacia", "diesel", 350),
                2, new Car("Lexus", "gasoline", 350),
                3, new Car("Chevrolet", "electric", 150),
                4, new Car("Mercedes", "gasoline", 150),
                5, new Car("Chevrolet", "diesel", 250),
                6, new Car("Ford", "electric", 80),
                7, new Car("Chevrolet", "diesel", 450),
                8, new Car("Mercedes", "electric", 200),
                9, new Car("Chevrolet", "gasoline", 350),
                10, new Car("Lexus", "diesel", 300)
        );
    }

    @AfterEach
    void destroy(){
        carInventoryList = null;
    }

    @Description(""" 
            Test Stream Comparators -
            /*1. If the horsepower values are different, then sort in descending order by horsepower
            * 2. If the horsepower values are equal, then sort in ascending order by the map keys
            * 3. The result, List<String>, should contain items of type key(horsepower) Under these statements,
            * sorting the cars map will result in: [7(450), 1(350), 2(350), 9(350), 10(300), 5(250),
                8(200), 3(150), 4(150), 6(80)]
            * */
            """)
    @Test
    void test_sortCarInventoryMap(){
        List<String> expectedResult = List.of("7(450)", "1(350)", "2(350)", "9(350)", "10(300)", "5(250)",
                "8(200)", "3(150)", "4(150)", "6(80)");

        List<String> actualResult = sortCarInventoryMap(carInventoryMap);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Stream Comparators - Sort Car By Brand")
    @Test
    void test_compareCarBySearchKey(){
        List<Car> actualResult = sortCarByBrand(carInventoryList);

        assertEquals("Chevrolet", actualResult.stream().findFirst().get().getBrand());
    }

    @Description("Test Stream Comparators - Sort Car By Horse Power")
    @Test
    void test_sortCarByHorsePower(){
        List<Car> actualResult = sortCarByHorsePower(carInventoryList);

        assertEquals(450, actualResult.stream().findFirst().get().getHorsePower());
    }

    public List<Car> buildCar(){
        return Arrays.asList(new Car("Dacia", "diesel", 100),
                new Car("Lexus", "gasoline", 300), new Car("Chevrolet", "electric", 150),
                new Car("Mercedes", "gasoline", 150), new Car("Chevrolet", "diesel", 250),
                new Car("Ford", "electric", 80), new Car("Chevrolet", "diesel", 450),
                new Car("Mercedes", "electric", 200), new Car("Chevrolet", "gasoline", 350),
                new Car("Lexus", "diesel", 300), new Car("Ford", "electric", 200)
        );
    }
}
