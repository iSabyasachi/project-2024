package org.example.functions;

import org.example.domain.arts.Album;
import org.example.domain.arts.Artist;
import org.example.domain.library.Author;
import org.example.domain.library.Book;
import org.example.domain.library.BookShelf;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.functions.MapMultiExample.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* 185. Working with mapMulti(): Explain and exemplify the JDK 16 mapMulti(). Provide a brief introduction,
    explain how it works in comparison with flatMap(), and point out when mapMulti() is a good fit.
    *
    * Implementation:
    * 1. The mapper is a buffer that implements the Consumer functional interface.
    * 2. Each Time we invoke Consumer::accept, it accumulates the elements in the buffer and passes them to the stream
    * pipeline.
    *
    * Usage: It’s recommended to use mapMulti when a few stream elements need to be replaced or when it’s easier to
    * use an imperative approach to generate the elements of the stream pipeline.
    *
    * Reference: https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
* */
public class MapMultiExampleSpec {
    List<Album> albums;
    List<Author> authors;

    @BeforeEach
    public void init() {
        albums = buildMockAlbums();
        authors = buildMockAuthors();
    }

    @TestFactory
    Collection<DynamicTest> testMapMultiForBuildingBookShelf() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test Build Book Shelves Using Multi Map. Check Flat Map Example",
                        () -> {
                            //List<BookShelf> bookShelves = buildBookShelfUsingMultiMap(authors);
                            List<BookShelf> bookShelves =  authors.stream().<BookShelf>mapMulti((author, consumer) -> {
                                for(Book book : author.getBooks()){
                                    consumer.accept(new BookShelf(author.getName(), book.getTitle()));
                                }
                            }).toList();
                            System.out.println(bookShelves);

                            assertEquals("Paulo Coelho", bookShelves.get(0).getAuthor());
                            assertEquals("Alchemist", bookShelves.get(0).getBook());
                        }),
                DynamicTest.dynamicTest("Test Build Book Shelves for published year greater than 2005. Check Flat Map Example",
                        () -> {
                            //List<BookShelf> bookShelves = buildBookShelfGt2005UsingMultiMap(authors);

                            List<BookShelf> bookShelves = authors.stream().<BookShelf>mapMulti((author, consumer) -> {
                                for(Book book : author.getBooks()){
                                    if(book.getPublishDate().getYear() > 2005){
                                        consumer.accept(new BookShelf(author.getName(), book.getTitle()));
                                    }
                                }
                            }).toList();


                            assertEquals("Paulo Coelho", bookShelves.get(0).getAuthor());
                            assertEquals("Adultary", bookShelves.get(0).getBook());
                        }),
                DynamicTest.dynamicTest("Test Build Book Shelves for published year greater than 2005 using Method Reference. Check Flat Map Example",
                        () -> {
                            List<BookShelf> bookShelves = buildBookShelfGt2005UsingMultiMapUsingMethodReference(authors);

                            assertEquals("Paulo Coelho", bookShelves.get(0).getAuthor());
                            assertEquals("Adultary", bookShelves.get(0).getBook());
                        })

        );
    }

    @TestFactory
    Collection<DynamicTest> testMapMultiForArtistAlbumPair() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test list of artist-album name pairs using mapMulti",
                        () -> {
                            //List<Map.Entry<String, String>> expectedResultList = listOfArtistAlbumNamePairsUsingMultiMap(albums);

                            List<Map.Entry<String, String>> expectedResultList = albums.stream().<Map.Entry<String, String>>mapMulti((album, consumer) -> {
                                for(Artist artist: album.getArtists()){
                                    consumer.accept(Map.entry(artist.getName(), album.getAlbumName()));
                                }
                            }).toList();
                            System.out.println(expectedResultList);

                            Map.Entry<String, String> expectedFirstResult = expectedResultList.get(0);
                            Map.Entry<String, String> expectedSecondResult = expectedResultList.get(1);
                            assertEquals("Sonu Nigam", expectedFirstResult.getKey());
                            assertEquals("Dhadkan", expectedFirstResult.getValue());
                            assertEquals("Alka Yagnik", expectedSecondResult.getKey());
                            assertEquals("Dhadkan", expectedSecondResult.getValue());
                        }),
                DynamicTest.dynamicTest("Test Artist:Album Pairs To Major Labels using mapMulti using Method Reference",
                        () -> {
                            //List<Map.Entry<String, String>> resultList = findArtistAlbumPairsToMajorLabelsUsingMapMulti(albums);

                            List<Map.Entry<String, String>> resultList = albums.stream().<Map.Entry<String, String>>mapMulti((album, consumer) -> {
                                for(Artist artist : album.getArtists()){
                                    consumer.accept(Map.entry(artist.getName()+':'+album.getAlbumName(), artist.getMajorLabels().stream()
                                            .collect(Collectors.joining(","))));
                                }

                            }).toList();

                            assertEquals("Sonu Nigam:Dhadkan", resultList.get(0).getKey());
                            assertEquals("T Series,Sony Music India", resultList.get(0).getValue());
                            assertEquals("Alka Yagnik:Dhadkan", resultList.get(1).getKey());
                            assertEquals("T Series,Sony Music India,Zee Music", resultList.get(1).getValue());
                        })

        );
    }

    @TestFactory
    Collection<DynamicTest> testMapMultiFunc() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test Find Even Items Percentage Using Multi Map",
                        () -> {
                            List<Double> expectedResult = Arrays.asList(2.02, 4.04);

                            List<Double> actualResult = findEvenItemsPercentageUsingMultiMapFunc(List.of(1, 2, 3, 4, 5));

                            assertEquals(actualResult, expectedResult);
                        }),
                DynamicTest.dynamicTest("Test Sum Of Even Items Percentage Using Multi Map", () -> {
                    Double expectedResult = BigDecimal.valueOf(6.06).setScale(2, RoundingMode.HALF_UP).doubleValue();

                    Double actualResult = sumOfEvenItemsPercentageUsingMultiMapFunc(List.of(1, 2, 3, 4, 5));

                    assertEquals(expectedResult, actualResult);
                })
        );
    }

    @DisplayName("Test Sum Of Even Items Percentage Using Map")
    @Test
    void test_sumOfEvenItemsPercentageUsingMapFunc() {
        Double expectedResult = BigDecimal.valueOf(6.06).setScale(2, RoundingMode.HALF_UP).doubleValue();

        Double actualResult = sumOfEvenItemsPercentageUsingMapFunc(List.of(1, 2, 3, 4, 5));

        assertEquals(expectedResult, actualResult);
    }

    public static List<Album> buildMockAlbums() {
        Artist sonuNigam = new Artist("Sonu Nigam", Boolean.TRUE, List.of("T Series", "Sony Music India"));
        Artist AlkaYagnik = new Artist("Alka Yagnik", Boolean.TRUE, List.of("T Series", "Sony Music India", "Zee Music"));
        Album dhadkan = new Album("Dhadkan", 50000D, List.of(sonuNigam, AlkaYagnik));
        Album jaanwar = new Album("Jaanwar", 100000D, List.of(sonuNigam, AlkaYagnik));
        return List.of(dhadkan, jaanwar);
    }

    List<Author> buildMockAuthors() {
        List<Book> books = List.of(new Book("Alchemist", LocalDate.of(1988, 01, 01)),
                new Book("Eleven Minutes", LocalDate.of(2003, 06, 01)),
                new Book("Adultary", LocalDate.of(2014, 01, 11)));
        return List.of(new Author("Paulo Coelho", books));
    }

    @AfterEach
    public void destroy() {
        albums = null;
        authors = null;
    }
}
