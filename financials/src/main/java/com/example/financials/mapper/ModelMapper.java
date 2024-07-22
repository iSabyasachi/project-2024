package com.example.financials.mapper;

import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.request.FundRequest;
import com.example.financials.request.InstrumentRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelMapper {
    public static FundModel mapFundRequestToModel(FundRequest fundRequest){
        FundModel fundModel = new FundModel();
        fundModel.setFundName(fundRequest.getFundName());

        return fundModel;
    }

    public static InstrumentModel mapInstrumentRequestToModel(InstrumentRequest instrumentRequest){
        InstrumentModel instrumentModel = new InstrumentModel();
        instrumentModel.setInstrumentName(instrumentRequest.getInstrumentName());

        return instrumentModel;
    }
}
