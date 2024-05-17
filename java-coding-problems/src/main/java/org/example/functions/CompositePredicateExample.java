package org.example.functions;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class CompositePredicateExample {

    public static <T> Predicate<T> asOneAnd(Predicate<T>... predicates){
        return Stream.of(predicates).reduce(p -> true, Predicate::and);
    }

    public static <T> Predicate<T> asOneOr(Predicate<T>... predicates){
        return Stream.of(predicates).reduce(p -> false, Predicate::or);
    }

}
