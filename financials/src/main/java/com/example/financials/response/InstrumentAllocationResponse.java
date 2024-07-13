package com.example.financials.response;

import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InstrumentAllocationResponse {
    private FundResponse fundResponse;
    private BigDecimal allocationPercentage;
    private Date allocationDate;
}
