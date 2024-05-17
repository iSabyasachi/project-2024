package org.example.functions;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.functions.ParseTextFunction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTextFunctionSpec {
    String text;

    @BeforeEach
    void init(){
        text = "test, a, 1, 4, 5, 0xf5, 0x5, 4.5d, 6, 5.6, 50000, " +
                "345, 4.0f, 6$3, 2$1.1, 5.5, 6.7, 8, a11, 3e+1, -11199, 55";
    }

    @Description("Test Function to parse data for Double using NumberUtils")
    @Test
    void test_parseTextToDoubleUsingNumberUtils(){
        List<Double> expectedResult = List.of(1.0, 4.0, 5.0, 5.0, 5.0, 4.5, 6.0, 5.6, 50000.0, 345.0, 4.0,
                5.5, 6.7, 8.0, 30.0, -11199.0, 55.0);

        List<Double> actualResult = parseTextToDoubleUsingNumberUtils(text);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Function to parse data for Double")
    @Test
    void test_parseTextToDouble(){
        List<Double> expectedResult = List.of(1.0, 4.0, 5.0, 5.0, 5.0, 4.5, 6.0, 5.6, 50000.0, 345.0, 4.0,
                5.5, 6.7, 8.0, 30.0, -11199.0, 55.0);

        List<Double> actualResult = parseTextToDouble(text);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Function to parse data for Integer")
    @Test
    void test_parseTextToInteger(){
        List<Integer> expectedResult = List.of(1, 4, 5, 5, 5,  6, 50000, 345, 8, -11199, 55);

        List<Integer> actualResult = parseTextToInteger(text);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Function to parse data")
    @Test
    void test_applyFunctionToParseData(){
        String expectedText = "1, 4, 5, 4.5d, 6, 5.6, 50000, " +
                "345, 4.0f, 5.5, 6.7, 8, 3e+1, -11199, 55";

        String actualText = applyFunctionToParseData(expectedText, ParseTextFunction::extractNumbers);

        assertEquals(expectedText, actualText);
    }

    @Description("Test Function to parse data using lambda")
    @Test
    void test_extractNumbersUsingLambda(){
        String expectedText = "1, 4, 5, 4.5d, 6, 5.6, 50000, " +
                "345, 4.0f, 5.5, 6.7, 8, 3e+1, -11199, 55";

        String actualText = applyFunctionToParseData(expectedText, ParseTextFunction::extractNumbersUsingLambda);

        assertEquals(expectedText, actualText);
    }

}
