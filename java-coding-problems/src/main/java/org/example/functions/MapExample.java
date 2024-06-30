package org.example.functions;

import org.example.domain.blogs.Post;
import org.example.domain.vehicle.Car;
import org.example.util.function.Filters;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByValue;
import static java.util.Map.entry;
import static java.util.stream.Collectors.*;
/*
 * map: <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 * mapToDouble: DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);
 * filter: Stream<T> filter(Predicate<? super T> predicate);
 * */
public class MapExample {
    public static Map<Integer, Car> findSortedTopXCarsWithMoreThanYHPUsingFilters(Map<Integer, Car> carMap, int horsePower, int size){
        return Filters.sortedByValue(carMap, car -> car.getHorsePower() >= horsePower,
                Comparator.comparingInt(Car::getHorsePower),
                size);
    }

    public static Map<Integer, Car> findTopNCarsWithMoreThanMHPUsingFilters(Map<Integer, Car> carMap, int horsePower, int size){
        return Filters.byValue(carMap, car -> car.getHorsePower() >= horsePower, size);
    }

    public static Map<Integer, Car> findTop5CarsByKey(Map<Integer, Car> carMap, int size){
        return Filters.byKey(carMap, num -> num <= 5, size);
    }

    public static Map<Integer, Car> findTop5CarsByKeySorted(Map<Integer, Car> carMap, int size){
        return Filters.sortByKey(carMap, num -> num <= 5, Integer::compare, size);
    }

    public static Map<Integer, Car> findTopFiveCarsWithMoreThan100HP(Map<Integer, Car> carMap){
        return carMap.entrySet().stream()
                .filter(carEntry -> carEntry.getValue().getHorsePower() > 100)
                .sorted(comparingByValue(comparingInt(Car::getHorsePower).reversed()))
                .limit(5)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, c2) -> c2, LinkedHashMap::new));
    }

    public static List<String> findAllElectricBrandsUsingEntrySet(Map<Integer, Car> carMap){
        return carMap.entrySet().stream()
                .filter(carEntry -> carEntry.getValue().getFuel().equals("Electric"))
                .map(carEntry -> carEntry.getValue().getBrand()).toList();
    }

    public static List<String> findAllElectricBrandsUsingValues(Map<Integer, Car> carMap){
        return carMap.values().stream()
                .filter(value -> value.getFuel().equals("Electric"))
                .map(value -> value.getBrand()).toList();
    }

    public static Map<String, List<Integer>> mapTagToPosts(List<Post> posts) {
        Map<String, List<Integer>> mapper = new HashMap<>();
        for (Post post : posts) {
            List<String> tags = post.allTags();
            for (String tag : tags) {
                List<Integer> postIds = mapper.getOrDefault(tag, new ArrayList<>());
                postIds.add(post.getId());
                mapper.put(tag, postIds);
            }
        }
        return mapper;
    }

    public static Map<String, List<Integer>> mapTagToPostsUsingMap(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> post.allTags()
                        .stream()
                        .map(tag -> entry(tag, post.getId())))
                .collect(groupingBy(
                        Map.Entry::getKey,
                        mapping(Map.Entry::getValue, toList())));
    }

    public static Map<String, List<Integer>> mapTagToPostsUsingMapMulti(List<Post> posts) {
        return posts.stream().<Map.Entry<String, Integer>>mapMulti((post, mapper) -> {
            for (String tag : post.allTags()) {
                mapper.accept(entry(tag, post.getId()));
            }
        }).collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())));
    }
}
