package org.example.functions;

import jdk.jfr.Description;
import org.example.domain.financials.Fund;
import org.example.domain.financials.Instrument;
import org.example.domain.financials.Issuer;
import org.example.domain.financials.enums.InstrumentCategory;
import org.example.domain.financials.enums.InstrumentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LambdaLazinessExampleSpec {
    LambdaLazinessExample lambdaLazinessExample;
    Fund fundPrimary;
    Fund fundSecondary;
    @BeforeEach
    void init(){
        lambdaLazinessExample = new LambdaLazinessExample();
        Issuer apple = new Issuer(16510L, "Apple Co.", "Technology Company");
        List<Instrument> appleInstruments = List.of(
                new Instrument(165101L, "Apple Debt Security 101", InstrumentType.DEBT_SECURITY, InstrumentCategory.TERM, apple),
                new Instrument(165102L, "Apple Debt Security 102", InstrumentType.DEBT_SECURITY, InstrumentCategory.DELAY_DRAW, apple),
                new Instrument(165103L, "Apple Debt Security 103", InstrumentType.DEBT_SECURITY, InstrumentCategory.REVOLVER, apple)
        );
        fundPrimary = new Fund(1L, "Apple Fund - Primary", appleInstruments, 1000000.00D);

        Issuer samsung = new Issuer(16511L, "Samsung Co.", "Technology Company");
        List<Instrument> samsungInstruments = List.of(
                new Instrument(165111L, "Samsung Debt Security 101", InstrumentType.DEBT_SECURITY, InstrumentCategory.TERM, samsung),
                new Instrument(165112L, "Samsung Debt Security 102", InstrumentType.DEBT_SECURITY, InstrumentCategory.DELAY_DRAW, samsung),
                new Instrument(165113L, "Samsung Debt Security 103", InstrumentType.DEBT_SECURITY, InstrumentCategory.REVOLVER, samsung)
        );
        fundSecondary = new Fund(2L, "Samsung Fund - Secondary", samsungInstruments, 500000.00D);
    }


    @Description("Test Allocate Fund Imperative Fasion")
    @Test
    void test_allocateFundImperativeFasion(){
        Double actualAllocationAmount = lambdaLazinessExample.allocateFundImperativeFasion(fundPrimary);

        assertEquals(actualAllocationAmount, 700000.00D);
    }

    @Description("Test Allocate Fund Functional Fasion")
    @Test
    void test_allocateFundFunctionalFasion(){
        Double actualAllocationAmountAppleFund = lambdaLazinessExample.allocateFundFunctionalFasion(fundPrimary);
        Double actualAllocationAmountSamsungFund = lambdaLazinessExample.allocateFundFunctionalFasion(fundSecondary);

        assertEquals(700000.00D, actualAllocationAmountAppleFund);
        assertEquals(700000.00D, fundPrimary.getAllocationAmount());
        assertEquals(350000.00D, actualAllocationAmountSamsungFund);
        assertEquals(350000.00D, fundSecondary.getAllocationAmount());
    }

    @AfterEach
    void destroy(){
        lambdaLazinessExample = null;
        fundPrimary = null;
        fundSecondary = null;
    }
}
