package org.example.domain.financials.enums;

public enum InstrumentType {

    EQUITY_SECURITY("Equity Security"),
    DEBT_SECURITY("Debt Security"),
    DERIVATIVE("Derivative"),
    ALTERNATIVE_INVESTMENT("Alternative Investment");

    String name;

    InstrumentType(String name) {
        this.name = name;
    }
}
