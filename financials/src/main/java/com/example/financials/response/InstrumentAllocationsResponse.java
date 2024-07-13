package com.example.financials.response;

import com.example.financials.model.AllocationModel;
import lombok.Data;

import java.util.List;

@Data
public class InstrumentAllocationsResponse {
    private InstrumentResponse instrument;
    private List<InstrumentAllocationResponse> instrumentAllocations;
}
