package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.blogs.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.example.functions.MapExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* 186. Streaming custom code to map: Imagine a class that shapes some blog posts. Each post is identified by a unique
* integer ID, and the post has several properties, including its tags. The tags of each post are actually represented
* as a string of tags separated by a hashtag (#). Whenever we need the list of tags for a given post, we can call the
* allTags() helper method. Our goal is to write a stream pipeline that extracts from this list of tags a Map<String,
* List<Integer>> containing, for each tag (key), the list of posts (value).
* */
public class MapExampleSpec {
    List<Post> posts;
    @BeforeEach
    void init(){
        posts = buildPosts();
    }

    @Description("Test Map Tag To Posts")
    @Test
    void test_mapTagToPosts(){
        Map<String, List<Integer>> actualResults = mapTagToPosts(posts);

        assertEquals(List.of(3, 4), actualResults.get("Happiness"));
    }

    @Description("Test Map Tag To Posts Using Map")
    @Test
    void test_mapTagToPostsUsingMap(){
        Map<String, List<Integer>> actualResults = mapTagToPostsUsingMap(posts);

        assertEquals(List.of(3, 4), actualResults.get("Happiness"));
    }

    @Description("Test Map Tag To Posts Using Map Multi")
    @Test
    void test_mapTagToPostsUsingMapMulti(){
        Map<String, List<Integer>> actualResults = mapTagToPostsUsingMapMulti(posts);

        assertEquals(List.of(3, 4), actualResults.get("Happiness"));
    }

    List<Post> buildPosts(){
        return List.of(new Post(1, "Solving Java Problems. Yehh!!!", "Java#Coding#Fun"),
                new Post(2, "Weekend Fun, Swimming!!!", "Swimming#Fun#Thrill"),
                new Post(3, "Who wants to play Tennis!", "Tennis#WorkOut#Happiness"),
                new Post(4, "Java Coding Problems book, a MUST read!!!", "Books#Java#Happiness")
        );
    }

    @AfterEach
    void destory(){
        posts = null;
    }

}
