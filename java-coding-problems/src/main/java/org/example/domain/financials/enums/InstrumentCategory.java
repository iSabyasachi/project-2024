package org.example.domain.financials.enums;

public enum InstrumentCategory {
    TERM("Term Loan"),
    REVOLVER("Revolver"),
    DELAY_DRAW("Delay Draw");
    String name;

    InstrumentCategory(String name) {
        this.name = name;
    }
}
