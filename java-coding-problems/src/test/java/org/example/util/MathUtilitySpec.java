package org.example.util;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.example.util.MathUtility.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilitySpec {

    @Description("Test Square of a Number")
    @Test
    void test_square(){
        assertEquals(16,  (Integer) square(4));
        assertEquals(16.0f, (Float) square(4.0f));
        assertEquals(16L, (Long) square(4L));
        assertEquals(16.0D, square(4D));
    }

    @Description("Test Fibonacci Using Memoization")
    @Test
    void test_fibonacciUsingMemoization(){
        // 0 1 1 2 3 5
        assertEquals(8, fibonacciUsingMemoization(6));
    }

    @Description("Test Fibonacci Using Recursion")
    @Test
    void test_fibonacciUsingRecursion(){
        // 0 1 1 2 3 5
        assertEquals(8, fibonacciUsingRecursion(6));
    }

    @Description("Test Factorial Using Memoization")
    @Test
    void test_factorialUsingMemoization(){

        assertEquals(120, factorialUsingMemoization(5));
    }

    @Description("Test Factorial Using Recursion")
    @Test
    void test_factorialUsingRecursion(){

        assertEquals(120, factorialUsingRecursion(5));
    }

    @Description("Test Factorial using Iteration")
    @Test
    void test_factorialUsingIteration(){

        assertEquals(120, factorialUsingIteration(5));
    }
}
