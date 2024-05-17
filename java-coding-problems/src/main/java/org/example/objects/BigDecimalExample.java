package org.example.objects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalExample {
    public static Double checkFloatingNumbers(Double a, Double b) {
        return BigDecimal.valueOf(Double.sum(a, b)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
