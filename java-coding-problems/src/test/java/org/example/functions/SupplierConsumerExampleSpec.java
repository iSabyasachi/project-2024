package org.example.functions;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.example.functions.SupplierConsumerExample.applySupplyAndConsumerOnCount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierConsumerExampleSpec {

    @Description("Test Supplier and Consumer on count")
    @Test
    void test_applySupplyAndConsumerOnCount(){
        assertEquals(0, applySupplyAndConsumerOnCount("counter"));
        assertEquals(0, applySupplyAndConsumerOnCount("supplier"));
        assertEquals(1, applySupplyAndConsumerOnCount("counter"));
        assertEquals(2, applySupplyAndConsumerOnCount("consumer"));
        assertEquals(2, applySupplyAndConsumerOnCount("counter"));
        assertEquals(2, applySupplyAndConsumerOnCount("supplier"));
        assertEquals(3, applySupplyAndConsumerOnCount("counter"));
    }

}
