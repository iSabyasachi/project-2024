package org.example.functions;

import org.example.domain.arts.Album;
import org.example.domain.library.Author;
import org.example.domain.library.Book;
import org.example.domain.library.BookShelf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.util.Utility.immutablePair;

/*
 * mapMulti : <R> Stream<R> mapMulti(BiConsumer<T, Consumer<R>> mapper)
 * map: <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 * mapToDouble: DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);
 * */
public class MapMultiExample {

    public static List<BookShelf> buildBookShelfUsingMultiMap(List<Author> authors) {
        return authors.stream().<BookShelf>mapMulti((author, bookShelfConsumer) -> {
            for (Book book : author.getBooks()) {
                bookShelfConsumer.accept(new BookShelf(author.getName(), book.getTitle()));
            }
        }).collect(Collectors.toList());
    }

    public static List<BookShelf> buildBookShelfGt2005UsingMultiMap(List<Author> authors) {
        return authors.stream().<BookShelf>mapMulti((author, bookShelfConsumer) -> {
            for (Book book : author.getBooks()) {
                if (book.getPublishDate().getYear() > 2005)
                    bookShelfConsumer.accept(new BookShelf(author.getName(), book.getTitle()));
            }
        }).collect(Collectors.toList());
    }

    public static List<BookShelf> buildBookShelfGt2005UsingMultiMapUsingMethodReference(List<Author> authors) {
        return authors.stream().mapMulti(Author::mapAuthorAndBookToBookShelf).collect(Collectors.toList());
    }

    public static List<Double> findEvenItemsPercentageUsingMultiMapFunc(List<Integer> inputList) {
        double percentage = .01;
        return inputList.stream().<Double>mapMulti((element, consumer) -> {
            if (element % 2 == 0)
                consumer.accept((double) element * (1 + percentage));
        }).collect(Collectors.toList());
    }

    public static Double sumOfEvenItemsPercentageUsingMultiMapFunc(List<Integer> inputList) {
        double percentage = .01;
        double sum = inputList.stream()
                .mapMultiToDouble((element, consumer) -> {
                    if (element % 2 == 0)
                        consumer.accept(element * (1 + percentage));
                }).sum();
        return BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double sumOfEvenItemsPercentageUsingMapFunc(List<Integer> inputList) {
        double percentage = .01;
        double sum = inputList.stream()
                .filter(element -> element % 2 == 0)
                .<Double>mapToDouble(element -> element * (1 + percentage)).sum();
        return BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static List<Map.Entry<String, String>> listOfArtistAlbumNamePairsUsingMultiMap(List<Album> albums) {
        return albums.stream().<Map.Entry<String, String>>mapMulti((album, artistAlbumPair) -> {
            album.getArtists().forEach(artist -> {
                Map.Entry<String, String> entryPair = immutablePair(artist.getName(), album.getAlbumName());
                artistAlbumPair.accept(entryPair);
            });
        }).collect(Collectors.toList());
    }

    public static List<Map.Entry<String, String>> findArtistAlbumPairsToMajorLabelsUsingMapMulti(List<Album> albums) {
        return albums.stream().<Map.Entry<String, String>>mapMulti(Album::artistAlbumPairsToMajorLabels).collect(Collectors.toList());
    }
}
