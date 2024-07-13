package com.example.financials.mapper;

import com.example.financials.entity.Allocation;
import com.example.financials.entity.Fund;
import com.example.financials.entity.Instrument;
import com.example.financials.model.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper {
    public static InstrumentModel mapToInstrumentModel(Instrument instrument) {
        InstrumentModel instrumentModel = new InstrumentModel();
        instrumentModel.setInstrumentName(instrument.getInstrumentName());
        instrumentModel.setInstrumentType(instrument.getInstrumentType());
        instrumentModel.setTicker(instrument.getTicker());
        instrumentModel.setSector(instrument.getSector());

        List<AllocationModel> allocationModels = new ArrayList<>();
        if(!CollectionUtils.isEmpty(instrument.getAllocations())){
            for(Allocation allocation : instrument.getAllocations()){
                AllocationModel allocationModel = getInstrumentAllocationModel(allocation);
                allocationModels.add(allocationModel);
            }
            instrumentModel.setAllocations(allocationModels);
        }

        return instrumentModel;
    }

    public static FundModel mapToFundModel(Fund fund){
        FundModel fundModel = new FundModel();
        fundModel.setFundName(fund.getFundName());
        fundModel.setFundType(fund.getFundType());
        fundModel.setCreationDate(fund.getCreationDate());
        fundModel.setManager(fund.getManager());

        List<AllocationModel> allocationModels = new ArrayList<>();
        if(!CollectionUtils.isEmpty(fund.getAllocations())){
            for(Allocation allocation : fund.getAllocations()){
                AllocationModel allocationModel = getFundAllocationModel(allocation);
                allocationModels.add(allocationModel);
            }
            fundModel.setAllocations(allocationModels);
        }

        return fundModel;
    }

    private static AllocationModel getInstrumentAllocationModel(Allocation allocation) {
        AllocationModel allocationModel = new AllocationModel();
        FundModel fundModel = new FundModel();
        fundModel.setFundName(allocation.getFund().getFundName());
        fundModel.setFundType(allocation.getFund().getFundType());
        fundModel.setManager(allocation.getFund().getManager());
        fundModel.setCreationDate(allocation.getFund().getCreationDate());
        allocationModel.setFundModel(fundModel);
        allocationModel.setAllocationPercentage(allocation.getAllocationPercentage());
        allocationModel.setAllocationDate(allocation.getAllocationDate());
        return allocationModel;
    }

    private static AllocationModel getFundAllocationModel(Allocation allocation) {
        AllocationModel allocationModel = new AllocationModel();
        InstrumentModel instrumentModel = new InstrumentModel();
        instrumentModel.setInstrumentName(allocation.getInstrument().getInstrumentName());
        instrumentModel.setInstrumentType(allocation.getInstrument().getInstrumentType());
        instrumentModel.setTicker(allocation.getInstrument().getTicker());
        instrumentModel.setSector(allocation.getInstrument().getSector());
        allocationModel.setInstrumentModel(instrumentModel);
        allocationModel.setAllocationPercentage(allocation.getAllocationPercentage());
        allocationModel.setAllocationDate(allocation.getAllocationDate());
        return allocationModel;
    }

}
