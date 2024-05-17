package org.example.util.function;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogPredicateSpec {
    @Description("Test Log Predicate")
    @Test
    void test_testAndLog(){
        LogPredicate<String> logPredicate = value -> value.startsWith("S");

        assertEquals(false, logPredicate.testAndLog("Mohan", "Sarang"));
    }
}
