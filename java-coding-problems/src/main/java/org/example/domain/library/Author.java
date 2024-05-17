package org.example.domain.library;

import lombok.Data;

import java.util.List;
import java.util.function.Consumer;

@Data
public class Author {
    private final String name;
    private final List<Book> books;

    public void mapAuthorAndBookToBookShelf(Consumer<BookShelf> bookShelfConsumer) {
        for (Book book : books) {
            if (book.getPublishDate().getYear() > 2005) {
                bookShelfConsumer.accept(new BookShelf(name, book.getTitle()));
            }
        }
    }
}
