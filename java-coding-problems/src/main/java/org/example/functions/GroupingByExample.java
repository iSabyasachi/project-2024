package org.example.functions;

import org.example.domain.blogs.BlogPost;
import org.example.domain.blogs.BlogPostType;
import org.example.domain.blogs.Post;
import org.example.domain.vehicle.Car;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Map.entry;
import static java.util.stream.Collectors.*;

public class GroupingByExample {

    /* Mapping Grouped Results to a Different Type */
    public static Map<BlogPostType, String> concatenateTitlesByPostType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType,
                mapping(BlogPost::getTitle, joining(", ",  "Post Titles [", "]"))));
    }

    /*
    * Aggregating Multiple Attributes of a Grouped Result
    * let’s group by author and for each one we count the number of titles, list the titles,
    * and provide a summary statistics of the likes.
    * */
    public static Map<String, BlogPost.PostCountTitlesLikesStats> postsPerAuthor(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getAuthor, collectingAndThen(toList(), list -> {
            long postCount = list.stream().map(BlogPost::getTitle).count();
            String titles = list.stream().map(BlogPost::getTitle).collect(joining(":"));
            IntSummaryStatistics likesStats = list.stream().collect(summarizingInt(BlogPost::getLikes));
            return new BlogPost.PostCountTitlesLikesStats(postCount, titles, likesStats);
        })));
    }

    /* Simple Grouping by a Single Column */
    public static Map<BlogPostType, List<BlogPost>> listOfPostPerType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType));
    }

    /* Modifying the Returned Map Value Type */
    public static Map<BlogPostType, Set<BlogPost>> postsPerType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType, toSet()));
    }

    /* Simple Grouping by a Single Column using Map Multi*/
    public static Map<BlogPostType, List<BlogPost>> postsPerTypeUsingMapMulti(List<BlogPost> posts){
        return posts.stream().<Map.Entry<BlogPostType, BlogPost>>mapMulti((post, mapper) -> {
            mapper.accept(entry(BlogPostType.valueOf(post.getType().name()), post));
        }).collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())));
    }

    /* Grouping by Multiple Fields */
    public static Map<String, Map<BlogPostType, List<BlogPost>>> groupPostsByAuthorAndType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));
    }

    /* Getting the Average from Grouped Results */
    public static Map<BlogPostType, Double> averageLikesPerType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));
    }

    /* Getting the Sum from Grouped Results */
    public static Map<BlogPostType, Integer> sumOfLikesPerType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));
    }

    /* Getting the Maximum or Minimum from Grouped Results */
    public static Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType(List<BlogPost> posts){
        return posts.stream()
                .collect(groupingBy(BlogPost::getType,
                maxBy(comparingInt(BlogPost::getLikes))));
    }

    /* Getting a Summary for an Attribute of Grouped Results */
    public static Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType(List<BlogPost> posts){
        return posts.stream().collect(groupingBy(BlogPost::getType, summarizingInt(BlogPost::getLikes)));
    }

    public static Map<String, List<Car>> listOfCarsGroupByFuel(List<Car> cars){
        return cars.stream().collect(groupingBy(Car::getFuel, toList()));
    }
}
