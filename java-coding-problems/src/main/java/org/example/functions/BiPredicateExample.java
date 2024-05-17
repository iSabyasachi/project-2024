package org.example.functions;

import com.sun.source.tree.SwitchTree;
import lombok.Builder;
import org.example.domain.vehicle.Car;
import org.example.enums.NumericPredicateBuilder;
import org.example.enums.PredicateBuilder;
import org.example.enums.StringPredicateBuilder;
import org.example.util.ReflectionUtility;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class BiPredicateExample {

    public static List<Car> getListOfCarsByFilterMap(List<Car> cars, Map<String, Object> filterMap, Map<String, String> filterCriteriaMap) {
        Predicate<Car> filterPredicate = t -> true;
        for (String key : filterMap.keySet()) {

            PredicateBuilder predicateBuilder = switch (filterCriteriaMap.get(key)) {
                case "EQUALS" -> StringPredicateBuilder.EQUALS;
                case "STARTS_WITH" -> StringPredicateBuilder.STARTS_WITH;
                case "GE" -> NumericPredicateBuilder.GE;
                default -> StringPredicateBuilder.CONTAINS;
            };

            filterPredicate = filterPredicate.and(predicateBuilder
                    .toPredicate(ReflectionUtility.getFieldByName(Car.class, key), filterMap.get(key)));
        }
        return cars.stream().filter(filterPredicate).toList();
    }

    public static List<Car> getListOfCarsStartsWithBrandName(List<Car> cars, String searchValue) {
        Function<Car, String> fieldByBrand = ReflectionUtility.getFieldByName(Car.class, "brand");

        Predicate<Car> startsWithPredicate = StringPredicateBuilder.STARTS_WITH.toPredicate(fieldByBrand, searchValue);

        return cars.stream().filter(startsWithPredicate).toList();
    }

    public static List<Car> getListOfCarsStartsWithBrandNameAndFuelType(List<Car> cars, String searchBrand, String searchFuelType) {
        Function<Car, String> fieldByBrand = ReflectionUtility.getFieldByName(Car.class, "brand");
        Function<Car, String> fieldByFuel = ReflectionUtility.getFieldByName(Car.class, "fuel");

        Predicate<Car> searchByBrancAndFuelPredicate = StringPredicateBuilder.STARTS_WITH.toPredicate(fieldByBrand, searchBrand).and(
                StringPredicateBuilder.EQUALS.toPredicate(fieldByFuel, searchFuelType)
        );

        return cars.stream().filter(searchByBrancAndFuelPredicate).toList();
    }

    public static List<Car> getListOfCarsHorsePowerGT(List<Car> cars, Integer searchValue) {
        Function<Car, Integer> fieldByNameFunc = ReflectionUtility.getFieldByName(Car.class, "horsePower");

        Predicate<Car> gtPredicate = NumericPredicateBuilder.GT
                .toPredicate(fieldByNameFunc, searchValue);

        return cars.stream().filter(gtPredicate).toList();
    }

    public static List<Car> getListOfCarsHorsePowerBetween(List<Car> cars, Integer firstValue, Integer secondValue) {
        Function<Car, Integer> fieldByNameFunc = ReflectionUtility.getFieldByName(Car.class, "horsePower");

        Predicate<Car> betweenPredicate = NumericPredicateBuilder.GE
                .toPredicate(fieldByNameFunc, firstValue)
                .and(NumericPredicateBuilder.LE.toPredicate(fieldByNameFunc, secondValue));

        return cars.stream().filter(betweenPredicate).toList();
    }
}
