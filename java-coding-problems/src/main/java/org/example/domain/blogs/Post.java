package org.example.domain.blogs;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public class Post {
    private final Integer id;
    private final String title;
    private final String tags;

    public List<String> allTags(){
        return Arrays.stream(tags.split("#")).toList();
    }

}
