package org.example.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.example.util.Utility.squareOfANumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilitySpec {
    @DisplayName("test square of a number using generics")
    @Test
    void test_squareOfANumber(){
        Integer expectedIntegerValue = squareOfANumber(2, t -> t * t);
        Double expectedDoubleValue = squareOfANumber(2.0, t -> t * t);

        assertEquals(expectedIntegerValue, 4);
        assertEquals(expectedDoubleValue, 4.0);
    }


    @DisplayName("test pair utility function using Map.Entry static method")
    @Test
    void test_pair() {
        Map.Entry<String, String> actualPair = Utility.immutablePair("key", "value");

        assertEquals("key", actualPair.getKey());
        assertEquals("value", actualPair.getValue());
    }
}
