package org.example.util.function;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.isEqual;

@SuppressWarnings("unchecked")
@FunctionalInterface
public interface CustomStream<T> {
    Stream<T> stream();

    static <T> CustomStream<T> from(Stream<T> stream){
        return () -> stream;
    }

    default <T> boolean contains(T item){
        return stream().anyMatch(isEqual(item));
    }

    default boolean containsAll(Stream<? extends T> items){
        Set<? extends T> set = toSet(items);
        if(set.isEmpty()) return true;

        return stream().filter(item -> set.remove(item)).anyMatch(any -> set.isEmpty());
    }

    default boolean containsAll(T... items){
        return containsAll(Stream.of(items));
    }

    default boolean containsAll(List<? extends T> items){
        return containsAll(items.stream());
    }

    default  boolean containsAny(Stream<? extends T> items){
        Set<? extends T> set = toSet(items);
        if(set.isEmpty()) return false;

        return stream().anyMatch(set::contains);
    }

    default boolean containsAny(T... items){
        return containsAny(Stream.of(items));
    }

    default boolean containsAny(List<? extends T> items){
        return containsAny(items.stream());
    }

    static <T> Set<? extends T> toSet(Stream<? extends T> items){
        return items.collect(Collectors.toSet());
    }

}