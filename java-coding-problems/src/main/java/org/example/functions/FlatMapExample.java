package org.example.functions;

import org.example.domain.library.Author;
import org.example.domain.library.Book;
import org.example.domain.library.BookShelf;

import java.util.List;

public class FlatMapExample {
    /*
     * Having List<Author> denoted as authors, write a stream pipeline that returns the List<Book> published greater than 2005.
     * */

    public static List<Book> listOfBooksPublishedIn2002UsingFlatMap(List<Author> authors) {
        return authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .filter(book -> book.getPublishDate().getYear() == 2002).toList();
    }

    /* Alternatively we can use Map Multi instead of Flat Map*/
    public static List<Book> listOfBooksPublishedIn2002UsingMapMulti(List<Author> authors) {
        return authors.stream().<Book>mapMulti((author, consumer) -> {
            for (Book book : author.getBooks()) {
                if (book.getPublishDate().getYear() == 2002) {
                    consumer.accept(book);
                }
            }
        }).toList();
    }

    /*Find the List<Author> with books published in 2002 using Map Multi*/
    public static List<Author> listOfAuthorsWithBooksPublishedIn2002UsingMapMulti(List<Author> authors) {
        return authors.stream().<Author>mapMulti((author, consumer) -> {
            for (Book book : author.getBooks()) {
                if (book.getPublishDate().getYear() == 2002) {
                    consumer.accept(author);
                }
            }
        }).toList();
    }

    /*Find the List<Author> with books published in 2002 using Any Match*/
    public static List<Author> listOfAuthorsWithBooksPublishedIn2002UsingAnyMatch(List<Author> authors) {
        return authors.stream().filter(author ->
                        author.getBooks().stream()
                                .anyMatch(book -> book.getPublishDate().getYear() == 2002))
                .toList();
    }

    /*Find the List<Author> with books published in 2002 using Remove If*/
    public static List<Author> listOfAuthorsWithBooksPublishedIn2002UsingRemoveIf(List<Author> authors) {
        authors.
                removeIf(author -> author.getBooks().stream()
                        .noneMatch(book -> book.getPublishDate().getYear() == 2002));
        return authors;
    }

    public static List<BookShelf> buildBookShelfGt2005UsingFlatMap(List<Author> authors) {
        return authors.stream().flatMap(author -> author.getBooks().stream()
                .filter(book -> book.getPublishDate().getYear() > 2005).map(
                        book -> new BookShelf(author.getName(), book.getTitle())
                )).toList();
    }
}
