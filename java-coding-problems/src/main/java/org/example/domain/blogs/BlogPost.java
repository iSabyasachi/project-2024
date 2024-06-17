package org.example.domain.blogs;

import lombok.Data;

import java.util.IntSummaryStatistics;

@Data
public class BlogPost {
    final String title;
    final String author;
    final BlogPostType type;
    final int likes;

    public record PostCountTitlesLikesStats(long postCount, String titles, IntSummaryStatistics likesStats){};
}
