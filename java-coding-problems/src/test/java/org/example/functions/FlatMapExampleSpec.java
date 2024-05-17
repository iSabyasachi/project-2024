package org.example.functions;

import org.example.domain.library.Author;
import org.example.domain.library.Book;
import org.example.domain.library.BookShelf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.functions.FlatMapExample.*;
import static org.example.functions.MapMultiExample.buildBookShelfUsingMultiMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatMapExampleSpec {
    List<Author> authors;

    @BeforeEach
    void init() {
        authors = buildMockAuthors();
    }

    @DisplayName("Test List Of Books Published In 2002 using Flat Map. Check Multi Map Example.")
    @Test
    void test_listOfBooksPublishedIn2002UsingFlatMap() {
        List<Book> books = listOfBooksPublishedIn2002UsingFlatMap(authors);

        assertEquals("Like Flowing River", books.get(0).getTitle());
    }

    @DisplayName("Test List Of Books Published In 2002 using Multi Map. Check Flat Map Example.")
    @Test
    void test_listOfBooksPublishedIn2002UsingMapMulti() {
        List<Book> books = listOfBooksPublishedIn2002UsingMapMulti(authors);

        assertEquals("Like Flowing River", books.get(0).getTitle());
    }

    @DisplayName("Test list Of Authors With Books Published In 2002 using Map Multi. Better to use MapMulti")
    @Test
    void test_listOfAuthorsWithBooksPublishedIn2002UsingMapMulti() {
        List<Author> actualAuthors = listOfAuthorsWithBooksPublishedIn2002UsingMapMulti(authors);

        assertEquals("Paulo Coelho", actualAuthors.get(0).getName());
    }

    @DisplayName("Test list Of Authors With Books Published In 2002 using Any Match.")
    @Test
    void test_listOfAuthorsWithBooksPublishedIn2002UsingAnyMatch() {
        List<Author> actualAuthors = listOfAuthorsWithBooksPublishedIn2002UsingAnyMatch(authors);

        assertEquals("Paulo Coelho", actualAuthors.get(0).getName());
    }

    @DisplayName("Test list Of Authors With Books Published In 2002 using Remove If.")
    @Test
    void test_listOfAuthorsWithBooksPublishedIn2002UsingRemoveIf() {
        List<Author> actualAuthors = listOfAuthorsWithBooksPublishedIn2002UsingRemoveIf(authors);

        assertEquals("Paulo Coelho", actualAuthors.get(0).getName());
    }

    @DisplayName("Test Build Book Shelves Using Flat Map. Check Multi Map Example")
    @Test
    void test_buildBookShelfUsingFlatMap() {
        List<BookShelf> bookShelves = buildBookShelfUsingMultiMap(authors);

        assertEquals("Paulo Coelho", bookShelves.get(0).getAuthor());
        assertEquals("Alchemist", bookShelves.get(0).getBook());
    }

    @DisplayName("Test Build Book Shelves for published year greater than 2005. Check Multi Map Example")
    @Test
    void test_buildBookShelfGt2005UsingFlatMap() {
        List<BookShelf> bookShelves = buildBookShelfGt2005UsingFlatMap(authors);

        assertEquals("Paulo Coelho", bookShelves.get(0).getAuthor());
        assertEquals("Adultary", bookShelves.get(0).getBook());
    }

    List<Author> buildMockAuthors() {
        List<Book> books = Arrays.asList(new Book("Alchemist", LocalDate.of(1988, 01, 01)),
                new Book("Eleven Minutes", LocalDate.of(2003, 06, 01)),
                new Book("Like Flowing River", LocalDate.of(2002, 06, 01)),
                new Book("Adultary", LocalDate.of(2014, 01, 11)));
        return Arrays.asList(new Author("Paulo Coelho", books));
    }

    @AfterEach
    void destroy() {
        authors = null;
    }
}
