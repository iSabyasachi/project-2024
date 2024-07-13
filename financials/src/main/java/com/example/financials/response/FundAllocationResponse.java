package com.example.financials.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FundAllocationResponse {
    private InstrumentResponse instrumentResponse;
    private BigDecimal allocationPercentage;
    private Date allocationDate;
}
