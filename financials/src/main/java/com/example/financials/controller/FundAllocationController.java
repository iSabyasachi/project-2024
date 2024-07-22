package com.example.financials.controller;

import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.request.FundRequest;
import com.example.financials.request.InstrumentRequest;
import com.example.financials.response.FundAllocationsResponse;
import com.example.financials.response.InstrumentAllocationsResponse;
import com.example.financials.service.CacheService;
import com.example.financials.service.FundAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.financials.mapper.ModelMapper.*;
import static com.example.financials.mapper.ResponseMapper.mapToFundAllocationResponse;
import static com.example.financials.mapper.ResponseMapper.mapToInstrumentAllocationResponse;

@RestController
@RequestMapping("/financials")
public class FundAllocationController {
    @Autowired
    FundAllocationService fundAllocationService;

    @Autowired
    CacheService cacheService;

    /*From both Redis and Postgres Data Store */
    @GetMapping("/fund/{id}")
    public FundAllocationsResponse getFundById(@PathVariable long id, @RequestParam boolean fromCache){
        return mapToFundAllocationResponse(fundAllocationService.findFundById(id, fromCache));
    }

    @PutMapping("/fund/{id}")
    public FundAllocationsResponse updateFundById(@PathVariable long id, @RequestParam boolean refreshCache, @RequestBody FundRequest fundRequest){
        return mapToFundAllocationResponse(fundAllocationService.updateFundById(id, refreshCache, mapFundRequestToModel(fundRequest)));
    }

    @GetMapping("/instrument/{id}")
    public InstrumentAllocationsResponse getInstrumentById(@PathVariable long id){
        return mapToInstrumentAllocationResponse(fundAllocationService.findInstrumentById(id, true));
    }

    @PutMapping("/instrument/{id}")
    public InstrumentAllocationsResponse updateInstrumentById(@PathVariable long id, @RequestBody InstrumentRequest instrumentRequest){
        return mapToInstrumentAllocationResponse(fundAllocationService.updateInstrumentById(id, mapInstrumentRequestToModel(instrumentRequest)));
    }

    /*Only from Redis data store*/
    @GetMapping("/cache/fund/{id}")
    @ResponseBody
    public FundAllocationsResponse getFundByIdFromCache(@PathVariable long id){
        return mapToFundAllocationResponse(cacheService.getFundByIdFromCache(id));
    }

    @GetMapping("/cache/instrument/{id}")
    @ResponseBody
    public InstrumentAllocationsResponse getInstrumentByIdFromCache(@PathVariable long id){
        return mapToInstrumentAllocationResponse(cacheService.getInstrumentByIdFromCache(id));
    }


    /*Only from postgres data store*/
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
