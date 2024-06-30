package org.example.functions;

import org.example.domain.library.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.example.functions.ExceptionsExample.readFiles;
import static org.example.functions.FlatMapExample.listOfBooksPublishedIn2002UsingFlatMap;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionsExampleSpec {
    @DisplayName("Throwing Checked Exceptions from Lamdas - readFiles - Wrap Run Time Exception with IO Exception")
    @Test
    void test_readFiles() {
        Path path = Path.of("files");

        IOException exception = assertThrows(IOException.class, () -> readFiles(List.of(path)));

        assertTrue(exception.getMessage().contains("Some files are corrupted"));
    }
}
