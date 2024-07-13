package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.people.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.example.functions.CollectorExample.listOfNamesGroupedByAge;
import static org.example.functions.StreamExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamExampleSpec {
    @Description("Stream.iterate: findEvenNumbers")
    @Test
    void test_findEvenNumbers(){
        assertEquals(List.of(0, 2, 4, 6, 8, 10), findEvenNumbers(10));
    }

    @Description("Stream.iterate: findFibonacci")
    @Test
    void test_findFibonacci(){
        assertEquals(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34), findFibonacci(10));
    }

    @Description("Stream->mapMulti: extractCharactersFromAList")
    @Test
    void test_extractCharactersFromAList(){
        List<String> words = List.of("Hello", "Sunshine");

        List<Character> actualResult = extractCharactersFromAList(words);

        assertEquals(13, actualResult.size());
    }

    @Description("Stream->flatMap: extractCharactersFromAList")
    @Test
    void test_extractCharactersFromAListUsingFlatMap(){
        List<String> words = List.of("Hello", "Sunshine");

        List<Character> actualResult = extractCharactersFromAListUsingFlatMap(words);
        System.out.println(actualResult);

        assertEquals(13, actualResult.size());
    }

}
