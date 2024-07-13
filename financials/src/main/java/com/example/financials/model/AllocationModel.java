package com.example.financials.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/*Acts as DTO.
Use Data Transfer Objects (DTOs) to detach entities from the session and avoid lazy loading Or Serialization issue.*/
@Data
public class AllocationModel {
    private InstrumentModel instrumentModel;
    private FundModel fundModel;
    private BigDecimal allocationPercentage;
    private Date allocationDate;
}
