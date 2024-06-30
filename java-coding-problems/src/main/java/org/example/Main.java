package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /*BiConsumer<List<Integer>, Integer> biConsumer = (List<Integer> nums, Integer num) -> {
            nums.add(num);
        };*/
        /*BiConsumer<List<Integer>, Integer> biConsumer = List::add;

        List<Integer> nums = new ArrayList<>();
        biConsumer.accept(nums, 1);
        biConsumer.accept(nums, 2);
        biConsumer.accept(nums, 3);
        biConsumer.accept(nums, 4);*/

        List<Integer> nums = new ArrayList<>();
        buildList(nums, 1, List::add);
        buildList(nums, 2, List::add);
        System.out.println(nums);
    }

    public static <T extends List<U>, U> List<U> buildList(T items, U val, BiConsumer<T, U> biConsumer){
        biConsumer.accept(items, val);
        return items;
    }

}