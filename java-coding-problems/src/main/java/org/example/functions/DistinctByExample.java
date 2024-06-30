package org.example.functions;

import org.example.domain.vehicle.Car;
import org.example.util.function.MyCollectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DistinctByExample {
    record CarRecord(String brand, String name, String fuel, Integer horsePower){}

    public static <T> Predicate<T> distinctByKeys(final Function<? super T, ?>... keyExtracters){
        Set<List<?>> seen = ConcurrentHashMap.newKeySet();

        return t ->
           seen.add(Arrays.stream(keyExtracters)
                  .map(keyExtracter -> keyExtracter.apply(t))
                  .toList());

    }

    public static List<CarRecord> findAllCarsDistinctByBrandAndNameUsingMultipleKeys(List<CarRecord> cars){
        return cars.stream().filter(distinctByKeys(CarRecord::brand, CarRecord::name)).toList();
    }

    /* This does not work for null values*/
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> function){
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(function.apply(t), Boolean.TRUE) == null;
    }

    /* This does not work for null values*/
    public static <T> Predicate<T> distinctByKeyUsingSet(Function<? super T, ?> function){
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(function.apply(t));
    }

    public static List<Car> findAllCarsDistinctByBrandUsingConcurrentHashMapAndSet(List<Car> cars){
        return cars.stream().filter(distinctByKeyUsingSet(Car::getBrand)).toList();
    }

    public static List<Car> findAllCarsDistinctByBrandUsingConcurrentHashMap(List<Car> cars){
        return cars.stream().filter(distinctByKey(Car::getBrand)).toList();
    }


    public static Map<String, Car> findAllCarsDistinctByBrandUsingHelper(List<Car> cars){
        return cars.stream()
                .collect(MyCollectors.distinctByKey(Car::getBrand));

    }

    public static Map<String, Car> findAllCarsDistinctByBrand(List<Car> cars){
        return cars.stream()
                .collect(Collectors.toMap(Car::getBrand,
                        Function.identity(), (c1, c2) -> c1));

    }
}
