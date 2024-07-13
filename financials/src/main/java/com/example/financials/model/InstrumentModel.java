package com.example.financials.model;

import lombok.Data;

import java.util.List;
/*Acts as DTO.
Use Data Transfer Objects (DTOs) to detach entities from the session and avoid lazy loading issues
Or Serialization issue
.*/
@Data
public class InstrumentModel {
    String instrumentName;
    String instrumentType;
    String ticker;
    String sector;
    private List<AllocationModel> allocations;
}
