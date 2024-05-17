package org.example.domain.financials;

import lombok.Data;

import java.util.Set;

@Data
public class Deal {
    private Long id;
    private String name;
    private Set<Fund> funds;

}
