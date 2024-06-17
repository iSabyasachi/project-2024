package org.example.functions;

import org.example.domain.vehicle.Car;
import org.example.util.function.Streams;
import java.util.List;

@SuppressWarnings("unchecked")
public class RemoveAllOrRetainAllExample {
    public static List<Car> applyRemoveAllOrRetainAll(List<Car> inventoryList, Car[] retainCars, Car removeCar, String filterFuel){
        return Streams.from(inventoryList.stream())
                .distinct()
                .retainAll(retainCars)
                .filter(car -> car.getFuel().equals(filterFuel))
                .removeAll(removeCar)
                .toList();
    }
}
