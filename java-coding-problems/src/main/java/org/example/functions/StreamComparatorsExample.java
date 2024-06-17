package org.example.functions;

import org.example.domain.vehicle.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StreamComparatorsExample {

    public static List<String> sortingACarInventoryMapRefactored(Map<Integer, Car> carInventoryMap){
        return carInventoryMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Car>comparingByValue(
                                Comparator.comparingInt(
                                        Car::getHorsePower).reversed())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(c -> c.getKey() + "("
                        + c.getValue().getHorsePower() + ")")
                .toList();
    }

    public static List<String> sortCarInventoryMap(Map<Integer, Car> carInventoryMap){
        return carInventoryMap.entrySet().stream().sorted((carEntry1, carEntry2) ->
                        carEntry2.getValue().getHorsePower() == carEntry1.getValue().getHorsePower() ?
                                carEntry1.getKey().compareTo(carEntry2.getKey()) :
                        Integer.valueOf(carEntry2.getValue().getHorsePower()).compareTo(Integer.valueOf(carEntry1.getValue().getHorsePower()))
                ).map(entry -> entry.getKey() + "(" + entry.getValue().getHorsePower() + ")").toList();
    }

    public static List<Car> sortCarByBrand(List<Car> carInventoryList){
        carInventoryList.sort(Comparator.comparing(Car::getBrand));
        return carInventoryList;
    }

    public static List<Car> sortCarByHorsePower(List<Car> carInventoryList){
        return carInventoryList.stream()
                .sorted(Comparator.comparing(Car::getHorsePower, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .reversed()).toList();
    }

}
