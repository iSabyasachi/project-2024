package com.example.financials.model;

import lombok.Data;

import java.util.List;

@Data
public class InstrumentModel {
    String instrumentName;
    String instrumentType;
    String ticker;
    String sector;
    private List<AllocationModel> allocations;
}
