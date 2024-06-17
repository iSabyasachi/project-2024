package org.example.util;

import java.util.Arrays;

public class MathUtility{
    /*Time Complexity O(N) & Space Complexity: O(N) for stack*/
    public static int fibonacciUsingMemoization(int num){
        int[] lookup = new int[num + 1];
        Arrays.fill(lookup, -1);
        int result =  fibonacciUsingMemoization(num, lookup);
        System.out.println(Arrays.toString(lookup));
        return result;
    }
    public static int fibonacciUsingMemoization(int num, int[] lookup){
        if(num == 0 || num == 1) {
            lookup[num] = num;
            return num;
        }

        int value = lookup[num];
        if(value == -1){
            value = fibonacciUsingMemoization(num - 1, lookup) + fibonacciUsingMemoization(num - 2, lookup);
            lookup[num] = value;
        }

        return value;
    }

    /*Time Complexity O(2^N) & Space Complexity O(N)*/
    public static int fibonacciUsingRecursion(int num){
        if(num == 0 || num == 1) return num;

        return fibonacciUsingRecursion(num - 1) + fibonacciUsingRecursion(num - 2);
    }

    /*Time Complexity: O(N) & Space Complexity: O(N) for stack*/
    public static int factorialUsingMemoization(int num){
        int[] lookup = new int[num + 1];
        Arrays.fill(lookup, -1);

        int result = factorialUsingMemoization(num, lookup);
        System.out.println(Arrays.toString(lookup));
        return result;
    }

    public static int factorialUsingMemoization(int num, int[] lookup){
        if(num == 0) {
            lookup[num] = 1;
            return 1;
        }

        int value = lookup[num];
        if(value == -1){
            value = factorialUsingMemoization(num - 1, lookup) * num;
            lookup[num] = value;
        }

        return value;
    }

    public static int factorialUsingIteration(int num){
        int res = 1;
        for(int i = 2; i <= num ; i++){
            res = res * i;
        }
        return res;
    }
    public static Integer factorialUsingRecursion(Integer num) {
        if(num == 0) return 0;
        if(num == 1) return 1;

        return num * factorialUsingRecursion(num - 1);
    }

    public static <T> T square(Number T){
        if(T instanceof Double) return (T)square(T.doubleValue());
        if(T instanceof Float) return (T)square(T.floatValue());
        if(T instanceof Long) return (T)square(T.longValue());
        if(T instanceof Integer) return (T)square(T.intValue());

        throw new NumberFormatException();
    }

    private static Double square(Double d){
        return d * d;
    }

    private static Float square(Float i){
        return i * i;
    }

    private static Long square(Long l){
        return l * l;
    }

    private static Integer square(Integer i){
        return i * i;
    }

}
