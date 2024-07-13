package com.example.financials.controller;

import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.response.FundAllocationsResponse;
import com.example.financials.response.InstrumentAllocationsResponse;
import com.example.financials.service.FundAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.financials.mapper.ResponseMapper.mapToFundAllocationResponse;
import static com.example.financials.mapper.ResponseMapper.mapToInstrumentAllocationResponse;

@RestController
@RequestMapping("/financials")
public class FundAllocationController {
    @Autowired
    FundAllocationService fundAllocationService;

    @GetMapping("/fund/{id}")
    public FundModel getFundById(@PathVariable long id){
        return fundAllocationService.findFundById(id);
    }

    @GetMapping("/instrument/{id}")
    public InstrumentModel getInstrumentById(@PathVariable long id){
        return fundAllocationService.findInstrumentById(id);
    }

    /*From postgres data store*/
    @GetMapping("/postgres/fund/{id}")
    @ResponseBody
    public FundAllocationsResponse getFundByIdFromPostgres(@PathVariable long id){
        return mapToFundAllocationResponse(fundAllocationService.getFundByIdFromPostgres(id));
    }

    @GetMapping("/postgres/instrument/{id}")
    @ResponseBody
    public InstrumentAllocationsResponse getInstrumentByIdFromPostgres(@PathVariable long id){
        return mapToInstrumentAllocationResponse(fundAllocationService.getInstrumentByIdFromPostgres(id));
    }
}
