package org.example.domain.financials;

import lombok.Data;

@Data
public class Issuer {
    private Long id;
    private String name;
    private String description;

    public Issuer(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
