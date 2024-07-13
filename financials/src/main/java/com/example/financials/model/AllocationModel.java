package com.example.financials.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AllocationModel {
    private InstrumentModel instrumentModel;
    private FundModel fundModel;
    private BigDecimal allocationPercentage;
    private Date allocationDate;
}
