package org.example.enums;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public enum NumericPredicateBuilder implements PredicateBuilder{
    GT((t, u) -> t > u),
    LT((t, u) -> t < u),
    GE((t, u) -> t >= u),
    LE((t, u) -> t <= u),
    EQ((t, u) -> Integer.compare(t, u) == 0),
    NOT_EQ((t, u) -> Integer.compare(t, u) != 0);

    private final BiPredicate<Integer, Integer> predicate;

    NumericPredicateBuilder(
            BiPredicate<Integer, Integer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public <T, U> Predicate<T> toPredicate(Function<T, U> getter, Object u) {
        if(!(u instanceof Integer)) return obj -> false;
        return obj -> this.predicate.test((Integer) getter.apply(obj), (Integer) u);
    }

}
