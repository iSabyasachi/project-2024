package org.example.domain.financials;

import lombok.Data;
import org.example.domain.financials.enums.InstrumentCategory;
import org.example.domain.financials.enums.InstrumentType;

@Data
public class Instrument {
    private Long id;
    private String name;
    private InstrumentType type;
    private InstrumentCategory category;
    private Issuer issuer;
    private Double amountIssued;

    public Instrument(Long id, String name, InstrumentType type, InstrumentCategory category, Issuer issuer){
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.issuer = issuer;
    }
}
