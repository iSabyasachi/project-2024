package org.example.domain.arts;

import lombok.Data;

import java.util.List;

@Data
public class Artist {
    private String name;
    private Boolean associatedMajorLabels;
    private List<String> majorLabels;

    public Artist(String name, Boolean associatedMajorLabels, List<String> majorLabels) {
        this.name = name;
        this.associatedMajorLabels = associatedMajorLabels;
        this.majorLabels = majorLabels;
    }

    public String getName() {
        return name;
    }

    public Boolean getAssociatedMajorLabels() {
        return associatedMajorLabels;
    }

    public List<String> getMajorLabels() {
        return majorLabels;
    }
}
