package org.example.util.function;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filters {
    private  Filters(){
        throw new AssertionError("cannot instantiate");
    }

    public static <K, V> Map<K, V> byKey(Map<K, V> entityMap, Predicate<K> predicate, int size){
        return entityMap.entrySet().stream().filter(entry -> predicate.test(entry.getKey()))
                .limit(size)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <K, V> Map<K, V> sortByKey(Map<K, V> entityMap, Predicate<K> predicate, Comparator<K> comparator, int size){
        return entityMap.entrySet().stream()
                .filter(entry -> predicate.test(entry.getKey()))
                .sorted(Map.Entry.comparingByKey(comparator.reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c2, LinkedHashMap::new));
    }

    public static <K, V> Map<K, V> byValue(Map<K, V> entityMap, Predicate<V> predicate, int size){
        return entityMap.entrySet().stream().filter(entry -> predicate.test(entry.getValue()))
                .limit(size)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <K, V> Map<K, V> sortedByValue(
            Map<K, V> entityMap, Predicate<V> predicate,
            Comparator<V> comparator,
            int size){
        return entityMap.entrySet().stream().filter(entry -> predicate.test(entry.getValue()))
                .sorted(Map.Entry.comparingByValue(comparator.reversed()))
                .limit(size)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c2,
                        LinkedHashMap::new));
    }
}
