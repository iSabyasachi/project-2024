package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.functions.RemoveAllOrRetainAllExample.applyRemoveAllOrRetainAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveAllOrRetainAllExampleSpec {
    List<Car> carInventoryList;
    @BeforeEach
    void init(){
        carInventoryList = buildCar();
    }

    @AfterEach
    void destroy(){
        carInventoryList = null;
    }
    @Description("Test RemoveAll Or RetainAll")
    @Test
    void test_applyRemoveAllOrRetainAll(){
        Car car1 = new Car("Lexus", "diesel", 300);
        Car car2 = new Car("Ford", "electric", 80);
        Car car3 = new Car("Chevrolet", "electric", 150);

        List<Car> actualResult = applyRemoveAllOrRetainAll(carInventoryList, new Car[]{car1, car2, car3}, car2, "electric");

        assertEquals(List.of(car3), actualResult);
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
