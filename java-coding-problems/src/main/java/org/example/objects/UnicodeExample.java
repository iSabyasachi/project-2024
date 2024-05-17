package org.example.objects;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Explaining and exemplifying UTF-8, UTF-16, and UTF-32: Provide a detailed explanation of what UTF-8, UTF-16, and UTF-32 are.
 * Include several snippets of code to show how these work in Java.
 *
 * Encoding: mapping from the real-world text to its binary representation
 * Charsets: The set of characters that are included in a mapping definition is formally called a charset. Ex: ASCII: 128 characters
 * Code Point: A code point is an integer reference to a particular character. Ex: T : Unicode(U+0054) :: ASCII(84)
 * UTF-32: Encoding scheme for Unicode that employs four bytes to represent every code point defined by Unicode
 *         It is space inefficient to use four bytes for every character.
 * UTF-8 : Another encoding scheme for Unicode which employs a variable length of bytes to encode. While it uses a single byte
 *           to encode characters generally, it can use a higher number of bytes if needed, thus saving space.
 *         Due to its space efficiency, is the most common encoding used on the web.
 * UTF-16: UTF- 16 uses 2 bytes (16 bits). UTF-16 is more adequate with BMP (Basic Multilingual Plane) characters that
 *           can be represented with 2 bytes.
 * Note: UTF-16 is usually better for in-memory representation while UTF-8 is extremely good for text files and network protocols.
 * */
public class UnicodeExample {
    public static void main(String[] args) throws IOException {
        // Character Encoding
        // Single-Byte Encoding
        // Multi-Byte Encoding
    }

    static String decodeText(String input, Charset charset, CodingErrorAction codingErrorAction) throws IOException {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        charsetDecoder.onMalformedInput(codingErrorAction);
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes(charset)), charsetDecoder))
                .readLine();
    }

    static String decodeText(String input, String encoding) throws IOException {
        // Character Encoding
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes()),
                        Charset.forName(encoding)))
                .readLine();
    }

    static String convertToBinary(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }
}
