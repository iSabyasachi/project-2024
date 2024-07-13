package org.example.functions;

import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StreamExample {
    public static List<Integer> findEvenNumbers(int max){
        return Stream.iterate(0, n -> n <= max, n -> n + 2).toList();
    }

    public static List<Integer> findFibonacci(int max){
        Stream<Map.Entry<Integer, Integer>> fibonacci = Stream.iterate(
                new SimpleEntry<>(0, 1),
                entry -> new SimpleEntry<>(entry.getValue(), entry.getKey() + entry.getValue())
                );
        return fibonacci.limit(max).map(pair -> pair.getKey()).toList();
    }

    public static List<Character> extractCharactersFromAListUsingFlatMap(List<String> words){
        return words.stream().flatMap(word -> word.chars().mapToObj(c -> (char) c)).toList();
    }
    public static List<Character> extractCharactersFromAList(List<String> words){
        return words.stream().<Character>mapMulti(
                (word, consumer) -> {
                    for(Character ch: word.toCharArray()){
                        consumer.accept(ch);
                    }
                }
        ).toList();
    }
}
