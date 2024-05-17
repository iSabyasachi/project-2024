package org.example.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.objects.BigDecimalExample.checkFloatingNumbers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalExampleSpec {
    @DisplayName("Test checkFloatingNumbers()")
    @Test
    void test_checkFloatingNumbers() {
        Double expectedResult = BigDecimal.valueOf(6.06)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();

        Double actualResult = checkFloatingNumbers(2.02, 4.04);

        assertEquals(expectedResult, actualResult);
    }
}
