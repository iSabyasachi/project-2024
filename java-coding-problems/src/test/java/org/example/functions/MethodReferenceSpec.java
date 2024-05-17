package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.vehicle.Bicycle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.functions.MethodReferenceExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* Method References are a special type of lambda expression. They're often used to create simple lambda expressions
* by referencing existing methods.
*
* Four types of method reference.
* 1. Static methods
* 2. Instance methods of particular objects
* 3. Instance methods of an arbitrary object of a particular type
* 4. Constructor
*
* When calling non-static methods, there is one main difference between a method reference and a lambda.
* A method reference calls the constructor immediately and only once (at method invocation (run()),
* the constructor is not called). On the other hand, lambdas are lazy. They call the constructor only
* at method invocation and at each such invocation (run()). Note: Check Printer class in Office package.

* */
public class MethodReferenceSpec {

    @Description("Test lambda function without Static Methods Reference")
    @Test
    void test_capitalizeElements(){
        List<String> actualResult = capitalizeElements(List.of("blue", "sky", "clear", "water"));

        assertEquals(List.of("Blue", "Sky", "Clear", "Water"), actualResult);
    }

    @Description("Test Static Methods Reference")
    @Test
    void test_capitalizeElementsUsingMethodReference(){
        List<String> actualResult = capitalizeElementsUsingMethodReference(List.of("blue", "sky", "clear", "water"));

        assertEquals(List.of("Blue", "Sky", "Clear", "Water"), actualResult);
    }

    @Description("Test lambda function without Instance methods of particular objects")
    @Test
    void test_sortBicycleByFrameSize(){
        List<Bicycle> expectedResult = List.of(
                new Bicycle("Harculex", 17),
                new Bicycle("Mangoosh", 19),
                new Bicycle("Hero", 22)
                );

        List<Bicycle> actualResult = sortBicycleByFrameSize(Arrays.asList(
                new Bicycle("Mangoosh", 19),
                new Bicycle("Harculex", 17),
                new Bicycle("Hero", 22)));

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Instance methods of particular objects Method Reference")
    @Test
    void test_sortBicycleByFrameSizeWithMethodReference(){
        List<Bicycle> expectedResult = List.of(
                new Bicycle("Harculex", 17),
                new Bicycle("Mangoosh", 19),
                new Bicycle("Hero", 22)
        );

        List<Bicycle> actualResult = sortBicycleByFrameSizeWithMethodReference(Arrays.asList(
                new Bicycle("Mangoosh", 19),
                new Bicycle("Harculex", 17),
                new Bicycle("Hero", 22)));

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test lambda function without Reference to an Instance Method of an Arbitrary Object of a Particular Type")
    @Test
    void test_sortNumbers(){
        List<Integer> actualResult = sortNumbers(Arrays.asList(1, 2, 5, 4, 7, 8, 9));

        assertEquals(List.of(1, 2, 4, 5, 7, 8, 9), actualResult);
    }

    @Description("Test Reference to an Instance Method of an Arbitrary Object of a Particular Type")
    @Test
    void test_sortNumbersWithMethodReference(){
        List<Integer> actualResult = sortNumbersWithMethodReference(Arrays.asList(1, 2, 5, 4, 7, 8, 9));

        assertEquals(List.of(1, 2, 4, 5, 7, 8, 9), actualResult);
    }

    @Description("Test Constructor Method Reference")
    @Test
    void test_buildBicyleUsingMethodReference(){
        Bicycle[] expectedResult = {
                new Bicycle("Hero", 0),
                new Bicycle("Herculex", 0),
                new Bicycle("Mangoosh", 0)
        };

        Bicycle[] actualResult = buildBicyleUsingMethodReference(Arrays.asList("Hero", "Herculex", "Mangoosh"));

        assertEquals(Arrays.stream(expectedResult).toList(), Arrays.stream(actualResult).toList());
    }
}
