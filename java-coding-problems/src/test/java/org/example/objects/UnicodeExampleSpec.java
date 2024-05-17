package org.example.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnicodeExampleSpec {
    @DisplayName("Test decodeText_Error()")
    @Test
    void test_decodeText_Error() throws IOException {
        // Character Encoding
        assertEquals("The fa?ade pattern is a software design pattern.",
                UnicodeExample.decodeText("The façade pattern is a software design pattern.", StandardCharsets.US_ASCII,
                        CodingErrorAction.IGNORE));

        assertEquals("The fa?ade pattern is a software design pattern.",
                UnicodeExample.decodeText("The façade pattern is a software design pattern.", StandardCharsets.US_ASCII,
                        CodingErrorAction.REPLACE));

    }

    @DisplayName("Test decodeText()")
    @Test
    void test_decodeText() throws IOException {
        // Character Encoding
        assertEquals(UnicodeExample.decodeText("The façade pattern is a software design pattern.",
                "US-ASCII"), "The fa��ade pattern is a software design pattern.");
        assertEquals(UnicodeExample.decodeText("The façade pattern is a software design pattern.",
                "UTF-8"), "The façade pattern is a software design pattern.");
    }

    @DisplayName("Test convertToBinary()")
    @Test
    void test_convertToBinary() throws UnsupportedEncodingException {
        //Single-Byte Encoding
        assertEquals(UnicodeExample.convertToBinary("T", "US-ASCII"), "01010100");

        //Multi-Byte Encoding
        assertEquals(UnicodeExample.convertToBinary("語", "Big5"),
                "11111111111111111111111110111011 01111001");

        //Unicode
        assertEquals(UnicodeExample.convertToBinary("T", "UTF-32"), "00000000 00000000 00000000 01010100");
        assertEquals(UnicodeExample.convertToBinary("T", "UTF-8"), "01010100");
        assertEquals(UnicodeExample.convertToBinary("語", "UTF-8"),
                "11111111111111111111111111101000 11111111111111111111111110101010 11111111111111111111111110011110");


    }
}
