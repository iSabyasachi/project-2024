package com.example.financials.response;

import com.example.financials.model.AllocationModel;
import com.example.financials.model.FundModel;
import lombok.Data;

import java.util.List;

@Data
public class FundAllocationsResponse {
    private FundResponse fund;
    private List<FundAllocationResponse> fundAllocations;
}
