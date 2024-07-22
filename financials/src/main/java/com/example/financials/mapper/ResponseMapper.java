package com.example.financials.mapper;

import com.example.financials.model.AllocationModel;
import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.response.*;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ResponseMapper {
    public static InstrumentAllocationsResponse mapToInstrumentAllocationResponse(InstrumentModel instrumentModel){
        InstrumentAllocationsResponse instrumentAllocationsResponse = new InstrumentAllocationsResponse();
        InstrumentResponse instrumentResponse = new InstrumentResponse();
        instrumentResponse.setInstrumentName(instrumentModel.getInstrumentName());
        instrumentResponse.setInstrumentType(instrumentModel.getInstrumentType());
        instrumentResponse.setTicker(instrumentModel.getTicker());
        instrumentResponse.setSector(instrumentModel.getSector());
        instrumentAllocationsResponse.setInstrument(instrumentResponse);

        List<InstrumentAllocationResponse> instrumentAllocations = new ArrayList<>();
        if(!CollectionUtils.isEmpty(instrumentModel.getAllocations())){
            InstrumentAllocationResponse instrumentAllocationResponse = new InstrumentAllocationResponse();
            for(AllocationModel allocationModel: instrumentModel.getAllocations()){
                FundResponse fundResponse = new FundResponse();
                fundResponse.setFundName(allocationModel.getFundModel().getFundName());
                fundResponse.setFundType(allocationModel.getFundModel().getFundName());
                fundResponse.setManager(allocationModel.getFundModel().getManager());
                fundResponse.setCreationDate(allocationModel.getFundModel().getCreationDate());
                instrumentAllocationResponse.setFundResponse(fundResponse);

                instrumentAllocationResponse.setAllocationPercentage(allocationModel.getAllocationPercentage());
                instrumentAllocationResponse.setAllocationDate(allocationModel.getAllocationDate());
                instrumentAllocations.add(instrumentAllocationResponse);
            }
            instrumentAllocationsResponse.setInstrumentAllocations(instrumentAllocations);
        }

        return instrumentAllocationsResponse;
    }

    public static FundAllocationsResponse mapToFundAllocationResponse(FundModel fundModel){
        FundAllocationsResponse fundAllocationsResponse = new FundAllocationsResponse();
        FundResponse fundResponse = new FundResponse();
        fundResponse.setFundName(fundModel.getFundName());
        fundResponse.setFundType(fundModel.getFundType());
        fundResponse.setManager(fundModel.getManager());
        fundResponse.setCreationDate(fundModel.getCreationDate());
        fundAllocationsResponse.setFund(fundResponse);

        List<FundAllocationResponse> fundAllocations = new ArrayList<>();
        if(!CollectionUtils.isEmpty(fundModel.getAllocations())){
            for(AllocationModel allocationModel: fundModel.getAllocations()){
                FundAllocationResponse fundAllocationResponse = getFundAllocationResponse(allocationModel);
                fundAllocations.add(fundAllocationResponse);
            }
            fundAllocationsResponse.setFundAllocations(fundAllocations);
        }

        return fundAllocationsResponse;
    }

    private static FundAllocationResponse getFundAllocationResponse(AllocationModel allocationModel) {
        FundAllocationResponse fundAllocationResponse = new FundAllocationResponse();
        InstrumentResponse instrumentResponse = new InstrumentResponse();
        instrumentResponse.setInstrumentName(allocationModel.getInstrumentModel().getInstrumentName());
        instrumentResponse.setInstrumentType(allocationModel.getInstrumentModel().getInstrumentType());
        instrumentResponse.setTicker(allocationModel.getInstrumentModel().getTicker());
        instrumentResponse.setSector(allocationModel.getInstrumentModel().getSector());
        fundAllocationResponse.setInstrumentResponse(instrumentResponse);

        fundAllocationResponse.setAllocationPercentage(allocationModel.getAllocationPercentage());
        fundAllocationResponse.setAllocationDate(allocationModel.getAllocationDate());
        return fundAllocationResponse;
    }
}
