package org.example.util;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class Utility {
    /* the entry created using static method of Map.Entry is immutable */
    public static <K, V> Map.Entry<K, V> immutablePair(K key, V value) {
        return Map.entry(key, value);
    }

    public static <T extends Number, R extends Number> R squareOfANumber(T t, Function<T, R> func) {
        return func.apply(t);
    }
}
