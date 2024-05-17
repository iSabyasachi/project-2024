package org.example.functions;


import org.apache.commons.lang3.StringUtils;
import org.example.domain.vehicle.Bicycle;
import org.example.domain.vehicle.BicycleComparator;

import java.util.List;

public class MethodReferenceExample {

    static List<String> capitalizeElements(List<String> inputList){
        return inputList.stream().map(input -> StringUtils.capitalize(input)).toList();
    }

    /* Ex: Static methods Reference Type*/
    static List<String> capitalizeElementsUsingMethodReference(List<String> inputList){
        return inputList.stream().map(StringUtils::capitalize).toList();
    }

    /* Ex: Instance methods of particular objects Reference Type*/
    static List<Bicycle> sortBicycleByFrameSize(List<Bicycle> bicycleList){
        return bicycleList.stream().sorted((a, b) -> new BicycleComparator().compare(a, b)).toList();
    }

    static List<Bicycle> sortBicycleByFrameSizeWithMethodReference(List<Bicycle> bicycleList){
        return bicycleList.stream().sorted(new BicycleComparator()::compare).toList();
    }

    /*Ex: Reference to an Instance Method of an Arbitrary Object of a Particular Type*/
    static List<Integer> sortNumbers(List<Integer> numbers){
        return numbers.stream().sorted((a, b) -> a.compareTo(b)).toList();
    }

    static List<Integer> sortNumbersWithMethodReference(List<Integer> numbers){
        return numbers.stream().sorted(Integer::compareTo).toList();
    }

    /*Ex: Reference to a Constructor*/
    static Bicycle[] buildBicyleUsingMethodReference(List<String> inputList){
        return inputList.stream().map(Bicycle::new).toArray(Bicycle[]::new);
    }
}
