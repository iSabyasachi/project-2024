package org.example.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public enum StringPredicateBuilder implements PredicateBuilder{
    EQUALS(String::equals),
    STARTS_WITH(String::startsWith),
    CONTAINS(String::contains);

    private final BiPredicate<String, String> predicate;
    private StringPredicateBuilder(BiPredicate<String, String> predicate){
        this.predicate = predicate;
    }

    /*public <T> Predicate<T> toPredicate(Function<T, String> getter, String u){
        return obj -> this.predicate.test(getter.apply(obj), u);
    }*/

    @Override
    public <T, U> Predicate<T> toPredicate(Function<T, U> getter, Object u) {
        if(!(u instanceof String)) return obj -> false;
        return obj -> this.predicate.test((String) getter.apply(obj), (String) u);
    }
}
