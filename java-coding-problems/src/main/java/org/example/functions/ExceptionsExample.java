package org.example.functions;

import org.example.util.function.Exceptions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ExceptionsExample {
    static void readFiles(List<Path> paths){
        paths.forEach((p) -> {
            try {
                readFile(p);
            } catch (IOException e) {
                //throw new RuntimeException(e);
                Exceptions.throwChecked(new IOException("Some files are corrupted", e));
            }
        });
    }

    private static void readFile(Path p) throws IOException {
        throw new IOException("Files Not Found");
    }
}
