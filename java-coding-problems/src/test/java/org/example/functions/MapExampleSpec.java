package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.blogs.Post;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.example.functions.MapExample.*;
import static org.junit.jupiter.api.Assertions.*;

public class MapExampleSpec {
    List<Post> posts;
    Map<Integer, Car> carMap;
    @BeforeEach
    void init(){
        posts = buildPosts();
        carMap = buildCarMap();
    }

    @Description("""
            Filtering a map: Write several snippets of code to highlight different use cases for filtering a map.
            """)
    @TestFactory
    Collection<DynamicTest> testMapFilter(){
        return Arrays.asList(
                DynamicTest.dynamicTest("using entrySet - returns a List<String> containing all the electric brands",() -> {
                    List<String> expectedResult = List.of("Honda-CRV", "Toyota-Rav4", "Tesla");
                    List<String> actualResult = findAllElectricBrandsUsingEntrySet(carMap);

                    assertTrue(expectedResult.containsAll(actualResult));
                }),
                DynamicTest.dynamicTest("using values - returns a List<String> containing all the electric brands",() -> {
                    List<String> expectedResult = List.of("Honda-CRV", "Toyota-Rav4", "Tesla");
                    List<String> actualResult = findAllElectricBrandsUsingValues(carMap);

                    assertTrue(expectedResult.containsAll(actualResult));
                }),
                DynamicTest.dynamicTest("that returns a Map of the top five cars with more than 100 horsepower:", () -> {
                    Map<Integer, Car> actualResult = findTopFiveCarsWithMoreThan100HP(carMap);

                    assertFalse(actualResult.isEmpty());
                    assertTrue(actualResult.keySet().size() == 5);
                    assertEquals("Porsha", actualResult.get(2).getBrand());
                }),
                DynamicTest.dynamicTest("Using Filters: that returns a Sorted Map of the top five cars with more than 100 horsepower:", () -> {
                    Map<Integer, Car> actualResult = findSortedTopXCarsWithMoreThanYHPUsingFilters(carMap, 100, 5);

                    assertFalse(actualResult.isEmpty());
                    assertTrue(actualResult.keySet().size() == 5);
                    assertEquals("Porsha", actualResult.get(2).getBrand());
                }),
                DynamicTest.dynamicTest("Using Filters: that returns a Map of the top five cars with more than 100 horsepower:", () -> {
                    Map<Integer, Car> actualResult = findTopNCarsWithMoreThanMHPUsingFilters(carMap, 100, 5);

                    assertFalse(actualResult.isEmpty());
                    assertTrue(actualResult.keySet().size() == 5);
                }),
                DynamicTest.dynamicTest("Using Filters: that returns a Top 5 Cars By Key:", () -> {
                    Map<Integer, Car> actualResult = findTop5CarsByKey(carMap, 5);

                    assertFalse(actualResult.isEmpty());
                    assertTrue(actualResult.keySet().size() == 5);
                }),
                DynamicTest.dynamicTest("Using Filters: that returns a Top 5 Cars By Key - Sorted:", () -> {
                    Map<Integer, Car> actualResult = findTop5CarsByKeySorted(carMap, 5);

                    assertFalse(actualResult.isEmpty());
                    assertTrue(actualResult.keySet().size() == 5);
                })
        );
    }
    private Map<Integer, Car> buildCarMap() {
        return Map.of(
                1, new Car("Honda-CRV", "Electric", 220),
                2, new Car("Porsha", "Gas", 500),
                3, new Car("Toyota-Camry", "Gas", 170),
                4, new Car("Hyundai-Venue", "Petrol", 150),
                5, new Car("Toyota-Rav4", "Electric", 225),
                6, new Car("Tesla", "Electric", 201),
                7, new Car("Honda-Civic", "Gas", 165)
        );
    }
    @Description("""
            Test Map Tag To Posts -
            186. Streaming custom code to map: Imagine a class that shapes some blog posts. Each post is identified by a unique
             integer ID, and the post has several properties, including its tags. The tags of each post are actually represented
             as a string of tags separated by a hashtag (#). Whenever we need the list of tags for a given post, we can call the
             allTags() helper method. Our goal is to write a stream pipeline that extracts from this list of tags a Map<String,
             List<Integer>> containing, for each tag (key), the list of posts (value).
            """)
    @TestFactory
    Collection<DynamicTest> testMapMultiFunc() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test Map Tag To Posts", () -> {
                    Map<String, List<Integer>> actualResults = mapTagToPosts(posts);

                    assertEquals(List.of(3, 4), actualResults.get("Happiness"));
                }),
                DynamicTest.dynamicTest("Test Map Tag To Posts Using Map", () -> {
                    Map<String, List<Integer>> actualResults = mapTagToPostsUsingMap(posts);

                    assertEquals(List.of(3, 4), actualResults.get("Happiness"));
                }),
                DynamicTest.dynamicTest("Test Map Tag To Posts Using Map Multi", () -> {
                    Map<String, List<Integer>> actualResults = mapTagToPostsUsingMapMulti(posts);

                    assertEquals(List.of(3, 4), actualResults.get("Happiness"));
                })
        );
    }

    private List<Post> buildPosts(){
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
