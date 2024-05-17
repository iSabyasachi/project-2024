package org.example.enums;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public interface PredicateBuilder {
    public <T, U> Predicate<T> toPredicate(Function<T, U> getter, Object U);
}
