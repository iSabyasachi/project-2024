package org.example.domain.arts;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.example.util.Utility.immutablePair;

@Data
public class Album {
    private String albumName;
    private Double albumCost;
    private List<Artist> artists;

    public Album(String albumName, Double albumCost, List<Artist> artists) {
        this.albumName = albumName;
        this.albumCost = albumCost;
        this.artists = artists;

    }

    public void artistAlbumPairsToMajorLabels(Consumer<Map.Entry<String, String>> consumer) {
        artists.stream().filter(artist -> artist.getAssociatedMajorLabels()).forEach(artist -> {
            String concatLabels = artist.getMajorLabels().stream().collect(Collectors.joining(","));
            consumer.accept(immutablePair(artist.getName() + ":" + albumName, concatLabels));
        });
    }

    public String getAlbumName() {
        return albumName;
    }

    public Double getAlbumCost() {
        return albumCost;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
