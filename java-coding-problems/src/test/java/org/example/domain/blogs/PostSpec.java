package org.example.domain.blogs;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostSpec {
    @Description("Test allTags")
    @Test
    void test_allTags(){
        Post mockPost = new Post(1, "My First Coding Hackathon!", "Hello#World");

        assertEquals(List.of("Hello", "World"), mockPost.allTags());
    }
}
