package org.example.functions;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.example.functions.CompositePredicateExample.asOneAnd;
import static org.example.functions.CompositePredicateExample.asOneOr;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositePredicateExampleSpec {
    @Description("simple predicate")
    @Test
    public void whenFilterList_thenSuccess(){
        List<String> names = Arrays.asList("Zebra", "Zorro", "Sedan", "Mercury");

        List<String> actualResults = names.stream().filter(name -> name.startsWith("Z")).toList();

        assertEquals(List.of("Zebra", "Zorro"), actualResults);
    }

    @Description("multiple filters")
    @Test
    public void whenFilterListWithMultipleFilters_thenSuccess(){
        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(name -> name.startsWith("Z"))
                .filter(name -> name.length() > 5)
                .toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("complex Predicate")
    @Test
    public void whenFilterListWithComplexPredicate_thenSuccess(){
        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(name -> name.startsWith("Z") && name.length() > 5)
                .toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("Combine Predicate with 'And' ")
    @Test
    public void whenFilterListWithCombinedPredicateUsingAnd_thenSuccess(){
        Predicate<String> filterByStartsWith = (name) ->  name.startsWith("Z");
        Predicate<String> filterByLength = (name) ->  name.length() > 5;

        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(filterByStartsWith.and(filterByLength))
                .toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("Combine Predicate with 'Or' ")
    @Test
    public void whenFilterListWithCombinedPredicateUsingOr_thenSuccess(){
        Predicate<String> filterByStartsWith = (name) ->  name.startsWith("Z");
        Predicate<String> filterByLength = (name) ->  name.length() > 5;

        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(filterByStartsWith.or(filterByLength))
                .toList();

        assertEquals(List.of("Zebra", "Zorro", "Zigzag", "Mercury"), actualResults);
    }

    @Description("Combine Predicate with 'Or' and 'negate' ")
    @Test
    public void whenFilterListWithCombinedPredicateUsingOrAndNegate_thenSuccess(){
        Predicate<String> filterByStartsWith = (name) ->  name.startsWith("Z");
        Predicate<String> filterByLength = (name) ->  name.length() > 5;

        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(filterByStartsWith.or(filterByLength.negate()))
                .toList();

        assertEquals(List.of("Zebra", "Zorro", "Zigzag", "Sedan"), actualResults);
    }

    @Description("Combine Predicate in line ")
    @Test
    public void whenFilterListWithCombinedPredicateInline_thenSuccess(){
        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(((Predicate<String>) name -> name.startsWith("Z")).and(name -> name.length() > 5))
                .toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("Collection of Predicate using reduce ")
    @Test
    public void whenFilterListWithCollectionOfPredicateUsingAnd_thenSuccess(){
        List<Predicate<String>> predicates = List.of(
                (name) -> name.startsWith("Z"),
                (name) -> name.length() > 5
        );

        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream()
                .filter(predicates.stream().reduce(predicate -> true, Predicate::and))
                .toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("Collection of Predicate using reduce 'And'")
    @Test
    public void whenFilterListWithCollectionOfPredicateUsingReduceAnd_thenSuccess(){
        Predicate<String> filterByStartsWith = (name) ->  name.startsWith("Z");
        Predicate<String> filterByLength = (name) ->  name.length() > 5;
        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream().filter(asOneAnd(filterByStartsWith, filterByLength)).toList();

        assertEquals(List.of("Zigzag"), actualResults);
    }

    @Description("Collection of Predicate using reduce 'And'")
    @Test
    public void whenFilterListWithCollectionOfPredicateUsingReduceOr_thenSuccess(){
        Predicate<String> filterByStartsWith = (name) ->  name.startsWith("Z");
        Predicate<String> filterByLength = (name) ->  name.length() > 5;
        List<String> names = Arrays.asList("Zebra", "Zorro", "Zigzag", "Sedan", "Mercury");

        List<String> actualResults = names.stream().filter(asOneOr(filterByStartsWith, filterByLength)).toList();

        assertEquals(List.of("Zebra", "Zorro", "Zigzag", "Mercury"), actualResults);
    }
}
