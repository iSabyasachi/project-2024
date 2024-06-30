package org.example.util.function;

import org.example.domain.vehicle.Car;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectors {
    /*
       There are cases when we need to compute different things and then collect only first N or skip first N
       elements, then a custom collector is welcome.
     * */
    public static <T> Collector<T, List<T>, List<T>> toUnmodifiableListKeep(int max){
        return Collector.of(
                ArrayList::new, // supplier
                (resultList, element) -> {
                    if(resultList.size() < max){
                        resultList.add(element);
                    }
                }, // accumulator
                (left, right) -> {left.addAll(right); return left;}, //combiner
                Collections::unmodifiableList // finalizer
        );
    }

    public static <T> Collector<T, ?, List<T>> toUnmodifiableListKeepRange(int fromIndex, int toIndex) {
        class SubList {
            int currentIndex;
            List<T> elements = new ArrayList<>();
        }

        return Collector.of(
                SubList::new, // Supplier
                (subList, value) -> {
                    if(subList.currentIndex >= fromIndex && subList.currentIndex < toIndex){
                        subList.elements.add(value);
                    }
                    subList.currentIndex++;
                }, // Accumulator
                (left, right) -> {
                    left.elements.addAll(right.elements);
                    left.currentIndex = left.currentIndex + right.currentIndex;
                    return left;
                }, // Combiner
                (subList) -> Collections.unmodifiableList(subList.elements) // Finisher
        );
    }

    public static <T> Collector<T, List<T>, List<T>> toUnmodifiableListSkip(int index){
        return Collector.of(
                ArrayList::new,
                (resultList, value) -> {
                    if(resultList.size() >= index){
                        resultList.add(value);
                    }else{
                        resultList.add(null);
                    }
                },
                (left, right) -> {left.addAll(right); return left;},
                (resultList) -> Collections.unmodifiableList(resultList.subList(index, resultList.size()))
        );
    }

    public static <T> Collector<T, ?, List<T>> toUnmodifiableListSkipUsingCustomSupplier(int index) {
        class SubList {
            int index;
            List<T> list = new ArrayList<>();
        }
        return Collector.of(
                SubList::new,
                (subList, value) -> {
                    if (subList.index >= index) {
                        subList.list.add(value);
                    } else {
                        subList.index++;
                    }
                },
                (left, right) -> {
                    left.list.addAll(right.list);
                    left.index = left.index + right.index;
                    return left;
                },
                (subList) -> Collections.unmodifiableList(subList.list)
        );
    }

    public static <K, T>  Collector<T, ?, Map<K, T>> distinctByKey(Function<? super T, ? extends K> keyMapper){
        return Collectors.toMap(keyMapper,
                Function.identity(), (c1, c2) -> c1);
    }

    public static <T> Collector<T, TreeSet<T>, TreeSet<T>> toTreeSet(){
        return Collector.of(
                TreeSet::new,
                TreeSet::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    public static <T> Collector<T, LinkedHashSet<T>, LinkedHashSet<T>> toLinkedHashSet(){
        return Collector.of(
                LinkedHashSet::new,
                HashSet::add,
                (left, right) -> {
                  left.addAll(right);
                  return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    public static <T, A, R> Collector<T, A, R> exclude(Predicate<T> predicate,
                                                                  Collector<T, A, R> myCollector){
        return Collector.of(
                myCollector.supplier(),
                (s, t) -> {
                    if(predicate.negate().test(t)){
                        myCollector.accumulator().accept(s, t);
                    }
                },
                myCollector.combiner(),
                myCollector.finisher(),
                myCollector.characteristics().toArray(Collector.Characteristics[]::new)
        );
    }

    public static <T, A extends T, R extends Collection<A>> Collector<T, ?, R> toType(Class<A> type, Supplier<R> supplier){
        return Collector.of(
                supplier,
                (R r, T t) -> {
                    if(type.isInstance(t)){
                        r.add(type.cast(t));
                    }
                },
                (R left, R right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }
}
