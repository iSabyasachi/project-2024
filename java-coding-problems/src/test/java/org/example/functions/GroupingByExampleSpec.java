package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.blogs.BlogPost;
import org.example.domain.blogs.BlogPostType;
import org.example.domain.blogs.Post;
import org.example.domain.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.example.functions.GroupingByExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupingByExampleSpec {
    List<Car> cars;
    List<BlogPost> posts;

    @BeforeEach
    void init(){
        cars = buildCar();
        posts = buildBlogs();
    }

    @Description("Test max likes per post type")
    @Test
    void test_maxLikesPerPostType(){
        Optional<BlogPost> expectedResult =
                Optional.of(new BlogPost("Solving Java Problems. Yehh!!!", "Sasank Acharya", BlogPostType.NEWS, 10));

        Map<BlogPostType, Optional<BlogPost>> actualResult =
                maxLikesPerPostType(posts);

        assertEquals(expectedResult, actualResult.get(BlogPostType.NEWS));
    }

    @Description("Test average likes per type")
    @Test
    void test_averageLikesPerType(){
        Map<BlogPostType, Double> expectedResult = Map.of(
                BlogPostType.REVIEW,
                9D,
                BlogPostType.NEWS,
                8D,
                BlogPostType.GUIDE,
                14.5D
        );

        Map<BlogPostType, Double> actualResult = averageLikesPerType(posts);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test sum of likes per type")
    @Test
    void test_sumOfLikesPerType(){
        Map<BlogPostType, Integer> expectedResult = Map.of(
                BlogPostType.REVIEW,
                9,
                BlogPostType.NEWS,
                24,
                BlogPostType.GUIDE,
                29
        );

        Map<BlogPostType, Integer> actualResult = sumOfLikesPerType(posts);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test group posts by author and then type")
    @Test
    void test_groupPostsByAuthorAndType(){
        Map<BlogPostType, List<BlogPost>> expectedResult = Map.of(
                BlogPostType.NEWS,
                List.of(
                        new BlogPost("AI Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7),
                        new BlogPost("Angular Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7)
                )
        );

        Map<String, Map<BlogPostType, List<BlogPost>>> actualResult = groupPostsByAuthorAndType(posts);

        assertEquals(expectedResult, actualResult.get("Sabyasachi Mohapatra"));
    }

    @Description("Test list of post per type using Map Multi")
    @Test
    void test_postsPerTypeUsingMapMulti(){
        List<BlogPost> expectedResult = List.of(
                new BlogPost("Solving Java Problems. Yehh!!!", "Sasank Acharya", BlogPostType.NEWS, 10),
                new BlogPost("AI Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7),
                new BlogPost("Angular Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7)
        );

        Map<BlogPostType, List<BlogPost>> actualResult = postsPerTypeUsingMapMulti(posts);

        assertEquals(expectedResult, actualResult.get(BlogPostType.NEWS));
    }

    @Description("Test list of post per type")
    @Test
    void test_listOfPostPerType(){
        Map<BlogPostType, List<BlogPost>> expectedResult = Map.of(
                BlogPostType.NEWS,
                List.of(
                        new BlogPost("Solving Java Problems. Yehh!!!", "Sasank Acharya", BlogPostType.NEWS, 10),
                        new BlogPost("AI Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7),
                        new BlogPost("Angular Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7)
                ),
                BlogPostType.REVIEW,
                List.of(
                        new BlogPost("Java Coding Problems book", "Anghel Leonard", BlogPostType.REVIEW, 9)
                ),
                BlogPostType.GUIDE,
                List.of(
                        new BlogPost("Course to learn Angular in 30 days", "Sasank Acharya", BlogPostType.GUIDE, 12),
                        new BlogPost("Course to learn AI in 3 months", "Saurav Saxena", BlogPostType.GUIDE, 17)
                )
        );

        Map<BlogPostType, List<BlogPost>> actualResult = listOfPostPerType(posts);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test Set of post per type")
    @Test
    void test_postsPerType(){
        Map<BlogPostType, Set<BlogPost>> expectedResult = Map.of(
                BlogPostType.NEWS,
                Set.of(
                        new BlogPost("Solving Java Problems. Yehh!!!", "Sasank Acharya", BlogPostType.NEWS, 10),
                        new BlogPost("AI Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7),
                        new BlogPost("Angular Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7)
                ),
                BlogPostType.REVIEW,
                Set.of(
                        new BlogPost("Java Coding Problems book", "Anghel Leonard", BlogPostType.REVIEW, 9)
                ),
                BlogPostType.GUIDE,
                Set.of(
                        new BlogPost("Course to learn Angular in 30 days", "Sasank Acharya", BlogPostType.GUIDE, 12),
                        new BlogPost("Course to learn AI in 3 months", "Saurav Saxena", BlogPostType.GUIDE, 17)
                )
        );

        Map<BlogPostType, Set<BlogPost>> actualResult = postsPerType(posts);

        assertEquals(expectedResult, actualResult);
    }

    @Description("Test like statistics per type")
    @Test
    void test_likeStatisticsPerType(){
        Map<BlogPostType, IntSummaryStatistics> actualResults = likeStatisticsPerType(posts);

        assertEquals(3, actualResults.get(BlogPostType.NEWS).getCount());
        assertEquals(8, actualResults.get(BlogPostType.NEWS).getAverage());
        assertEquals(10, actualResults.get(BlogPostType.NEWS).getMax());
        assertEquals(7, actualResults.get(BlogPostType.NEWS).getMin());
        assertEquals(24, actualResults.get(BlogPostType.NEWS).getSum());
    }

    @Description("Test Aggregating Multiple Attributes of a Grouped Result")
    @Test
    void test_postsPerAuthor(){
        Map<String, BlogPost.PostCountTitlesLikesStats> actualResult = postsPerAuthor(posts);

        assertEquals(2, actualResult.get("Sabyasachi Mohapatra").postCount());
        assertEquals("AI Revolution:Angular Revolution", actualResult.get("Sabyasachi Mohapatra").titles());
        assertEquals(14, actualResult.get("Sabyasachi Mohapatra").likesStats().getSum());
    }

    @Description("Test Mapping Grouped Results to a Different Type")
    @Test
    void test_concatenateTitlesByPostType(){
        String expectedResult = "Post Titles [Solving Java Problems. Yehh!!!, AI Revolution, Angular Revolution]";
        Map<BlogPostType, String> actualResult = concatenateTitlesByPostType(posts);

        assertEquals(expectedResult, actualResult.get(BlogPostType.NEWS));
    }

    @Description("Test fetch list of car group by fuel type")
    @Test
    void test_listOfCarsGroupByFuel(){
        List<Car> expectedResult = List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Toyota-Rav4", "Hybrid", 225),
                new Car("Honda-Accord", "Hybrid", 201)
        );
        Map<String, List<Car>> actualResult = listOfCarsGroupByFuel(cars);

        assertEquals(actualResult.get("Hybrid"), expectedResult);
    }

    public List<Car> buildCar(){
        return List.of(
                new Car("Honda-CRV", "Hybrid", 220),
                new Car("Porsha", "Gas", 500),
                new Car("Toyota-Camry", "Gas", 170),
                new Car("Hyundai-Venue", "Petrol", 150),
                new Car("Toyota-Rav4", "Hybrid", 225),
                new Car("Honda-Accord", "Hybrid", 201),
                new Car("Honda-Civic", "Gas", 165)
        );
    }

    public List<BlogPost> buildBlogs(){
        return List.of(
                new BlogPost("Solving Java Problems. Yehh!!!", "Sasank Acharya", BlogPostType.NEWS, 10),
                new BlogPost("Java Coding Problems book", "Anghel Leonard", BlogPostType.REVIEW, 9),
                new BlogPost("Course to learn Angular in 30 days", "Sasank Acharya", BlogPostType.GUIDE, 12),
                new BlogPost("AI Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7),
                new BlogPost("Course to learn AI in 3 months", "Saurav Saxena", BlogPostType.GUIDE, 17),
                new BlogPost("Angular Revolution", "Sabyasachi Mohapatra", BlogPostType.NEWS, 7)
        );
    }

    @AfterEach
    void destory(){
        cars = null;
        posts = null;
    }
}
