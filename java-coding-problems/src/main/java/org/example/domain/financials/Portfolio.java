package org.example.domain.financials;

import lombok.Data;

import java.util.Set;

@Data
public class Portfolio {
    private Long id;
    private String name;
    private Set<Asset> assets;
    private Set<Instrument> debts;
}
