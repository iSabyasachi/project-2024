package org.example.functions;

import org.example.domain.vehicle.Car;
import org.example.domain.vehicle.Submersible;
import org.example.domain.vehicle.Vehicle;
import org.example.util.function.MyCollectors;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 static <T, R> Collector<T, R, R> of(Supplier<R> supplier, Accumulator<R, T> accumulator, BinaryOperator<R> combiner,
  Collector.Characteristics... characteristics)

 static of(Supplier<R> supplier, Accumulator<R, T> accumulator, BinaryOperator<R> combiner, Function<R> finisher,
  Collector.Characteristics... characteristics)
* */

public class CollectorExample {

    public static List<Vehicle> findFirstNCarsUsingCustomCollector(List<Vehicle> vehicles, int max){
        return vehicles.stream().collect(MyCollectors.toUnmodifiableListKeep(max));
    }

    public static List<Vehicle> findRangeOfCarsUsingCustomCollectorAndCustomSupplier(List<Vehicle> vehicles, int fromIndex, int toIndex){
        return vehicles.stream().collect(MyCollectors.toUnmodifiableListKeepRange(fromIndex, toIndex));
    }

    public static List<Vehicle> skipFirstNCarsUsingCustomCollector(List<Vehicle> vehicles, int index){
        return vehicles.stream().collect(MyCollectors.toUnmodifiableListSkip(index));
    }

    public static List<Vehicle> skipFirstNCarsUsingCustomCollectorAndCustomSupplier(List<Vehicle> vehicles, int index){
        return vehicles.stream().collect(MyCollectors.toUnmodifiableListSkipUsingCustomSupplier(index));
    }


    public static List<Vehicle> findFirstNCarsUsingLimit(List<Vehicle> vehicles, int n){
        return vehicles.stream().limit(n).toList();
    }

    public static List<Vehicle> eliminateFirstNCarsUsingSkip(List<Vehicle> vehicles, int n){
        return vehicles.stream().skip(n).toList();
    }

    public static int summingIntExample(List<Integer> numbers){
        return numbers.parallelStream().collect(
                Collector.of(
                        () -> new int[1], // supplier
                        (subtotal, number) -> subtotal[0] += number, // accumulator
                        (subtotal1, subtotal2) -> {subtotal1[0] += subtotal2[0]; return subtotal1;}, // combiner
                        (subtotal) -> subtotal[0] // finisher
                )
        );
    }
    public static TreeSet<String> collectCarsAndReturnTreeSet(Map<Integer, Car> carInventory){
        return carInventory.values()
                .stream()
                .filter(car -> "Electric".equals(car.getFuel()))
                .map(car -> car.getBrand())
                .collect(MyCollectors.toTreeSet());
    }

    public static TreeSet<Integer> collectSortedHPGT100andReturnTreeSet(Map<Integer, Car> carInventory){
        return carInventory.values()
                .stream().filter(car -> car.getHorsePower() > 100)
                .map(car -> car.getHorsePower())
                .collect(MyCollectors.toTreeSet());
    }

    public static LinkedHashSet<Integer> collectOrderedHPGT100andReturnLinkedHashSet(Map<Integer, Car> carInventory){
        return carInventory.values()
                .stream().filter(car -> car.getHorsePower() > 100)
                .map(car -> car.getHorsePower())
                .collect(MyCollectors.toLinkedHashSet());
    }

    public static LinkedHashSet<Integer> excludesElementsFromAnotherCollector(Map<Integer, Car> carInventory){
        Collectors.toCollection(LinkedHashSet::new);
        return carInventory.values()
                .stream()
                .map(car -> car.getHorsePower())
                .sorted()
                .collect(MyCollectors.exclude((hp -> hp > 200), Collectors.toCollection(LinkedHashSet::new)));
    }

    public static List<Car> collectsElementsByTypeOnlyCar(List<Vehicle> vehicles){
        return vehicles.stream().collect(MyCollectors.toType(Car.class, ArrayList::new));
    }

    public static HashSet<Submersible> collectsElementsByTypeOnlySubmersible(List<Vehicle> vehicle){
        return vehicle.stream().collect(MyCollectors.toType(Submersible.class, HashSet::new));
    }

    public static boolean customCollectorForSplayTree(){
        return false;
    }

}
