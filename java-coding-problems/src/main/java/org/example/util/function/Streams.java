package org.example.util.function;

import org.example.domain.vehicle.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public interface Streams<T> extends Stream<T> {

    static <T> Streams<T> from(Stream<? extends T> stream){
        if(stream == null) return from(Stream.empty());
        if(stream instanceof Streams) return (Streams<T>)stream;

        return new StreamWrapper<>(stream);
    }

    default Streams<T> removeAll(Stream<? extends T> items){
        Set<? extends T> set = toSet(items);
        if(set.isEmpty()) return this;

        return filter(item -> !set.contains(item)).onClose(items::close);
    }

    default Streams<T> remove(T item){
        return removeAll(item);
    }

    default Streams<T> removeAll(T... items){
        return removeAll(Arrays.stream(items));
    }

    default Streams<T> removeAll(List<? extends T> items){
        return removeAll(items.stream());
    }

    default Streams<T> retainAll(Stream<? extends T> items){
        Set<? extends T> set = toSet(items);
        if(set.isEmpty()) return from(Stream.empty());

        return filter(item -> set.contains(item)).onClose(items::close);
    }

    default Streams<T> retainAll(T... items){
        return retainAll(Stream.of(items));
    }

    default Streams<T> retainAll(List<? extends T> items){
        return retainAll(items.stream());
    }

    default <T> boolean containsAll(Stream<? extends T> stream){
        Set<? extends T> set = toSet(stream);

        return filter(item -> set.remove(item)).anyMatch(any -> set.isEmpty());
    }

    default <T> boolean containsAll(List<? extends T> items){
        return containsAll(items.stream());
    }

    default <T> boolean containsAll(T... items){
        return containsAll(Arrays.stream(items));
    }

    default <T> boolean containsAny(Stream<? extends T> items){
        Set<T> set = toSet(items);
        if(set.isEmpty()) return false;

        return anyMatch(set::contains);
    }

    default <T> boolean containsAny(List<? extends T> items){
        return containsAny(items.stream());
    }

    default <T> boolean containsAny(T... items){
        return containsAny(Arrays.stream(items));
    }

    static <T> Set toSet(Stream<? extends T> items){
        return items.collect(Collectors.toSet());
    }

    @Override
    public Streams<T> filter(Predicate<? super T> predicate);

    @Override
    public Streams<T> distinct();

    @Override
    public void forEach(Consumer<? super T> action);

    @Override
    public Streams<T> onClose(Runnable closeHandler);

    @Override
    public void close();

}
