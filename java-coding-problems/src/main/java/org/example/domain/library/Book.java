package org.example.domain.library;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private final String title;
    private final LocalDate publishDate;
}
