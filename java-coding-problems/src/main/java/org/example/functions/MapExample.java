package org.example.functions;

import org.example.domain.blogs.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import static java.util.stream.Collectors.*;
/*
 * map: <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 * mapToDouble: DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);
 * */
public class MapExample {

    /*
     * Our goal is to write a stream pipeline that extracts from this list of tags a Map<String,
     * List<Integer>> containing, for each tag (key), the list of posts (value).
     * */

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
