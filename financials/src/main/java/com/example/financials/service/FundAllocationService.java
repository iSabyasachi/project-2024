package com.example.financials.service;

import com.example.financials.entity.Fund;
import com.example.financials.entity.Instrument;
import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.repository.FundRepository;
import com.example.financials.repository.InstrumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.Objects;

import static com.example.financials.mapper.EntityMapper.mapToFundModel;
import static com.example.financials.mapper.EntityMapper.mapToInstrumentModel;

@Service
public class FundAllocationService {
    static Logger logger = LoggerFactory.getLogger(FundAllocationService.class);

    @Autowired
    FundRepository fundRepository;

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    CacheService cacheService;

    /* From Postgres Data Store Only*/
    public FundModel getFundByIdFromPostgres(long id){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("fundRepository.findById(id)");
        Fund fund = fundRepository.findById(id).orElse(null);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        logger.info("Total time: " + stopWatch.getTotalTimeMillis() + " ms");

        if(Objects.nonNull(fund)){
            logger.info("From Postgres Database only, Fund Data -> ", fund);
            return mapToFundModel(fund);
        }

        logger.info("From Postgres Database only, Fund Data Not Found");
        return null;
    }

    public InstrumentModel getInstrumentByIdFromPostgres(long id){
        Instrument instrument = instrumentRepository.findById(id).orElse(null);
        if(Objects.nonNull(instrument)){
            logger.info("From Postgres Database only, Instrument Data -> ", instrument);
            return mapToInstrumentModel(instrument);
        }

        logger.info("From Postgres Database only, Instrument Data Not Found");
        return null;
    }

    /*From Redis and Postgres Data Store */
    @Transactional(readOnly = true)
    public FundModel findFundById(long id, boolean fromCache){
        FundModel fundModel = fromCache ? cacheService.getFundByIdFromCache(id) : null;
        if(fundModel == null){
            logger.info("Fetch Fund Data From Postgres Database");
            Fund fund = fundRepository.findById(id).orElse(null);
            if(Objects.nonNull(fund)){
                fundModel = mapToFundModel(fund);

                cacheService.updateFundInCache(id, fundModel);
                return fundModel;
            }
        }

        return fundModel;
    }

    @Transactional
    public FundModel updateFundById(long id, boolean refreshCache, FundModel updateFundModel) {
        logger.info("Fetch and update Fund Data in Postgres Database!!!");
        Fund fund = (Fund) fundRepository.findById(id).orElse(null);
        if (fund == null) return null;

        fund.setFundName(updateFundModel.getFundName());
        FundModel updatedFundModel = mapToFundModel(fund);

        if (refreshCache){
            cacheService.updateFundInCache(id, updatedFundModel);
        }

        return updatedFundModel;
    }


    @Transactional(readOnly = true)
    public InstrumentModel findInstrumentById(long id, boolean fromCache){
        InstrumentModel instrumentModel = fromCache ? cacheService.getInstrumentByIdFromCache(id) : null;
        if(instrumentModel == null){
            logger.info("Fetch Instrument Data From Postgres Database!!!");
            Instrument instrument = instrumentRepository.findById(id).orElse(null);
            if(Objects.nonNull(instrument)){
                instrumentModel = mapToInstrumentModel(instrument);

                cacheService.updateInstrumentInCache(id, instrumentModel);
                return instrumentModel;
            }
        }

        return instrumentModel;
    }

    @Transactional
    public InstrumentModel updateInstrumentById(long id, InstrumentModel updateInstrumentModel){
        logger.info("Fetch and update Instrument Data in Postgres Database!!!");
        Instrument instrument = (Instrument)instrumentRepository.findById(id).orElse(null);
        if(instrument == null) return null;

        instrument.setInstrumentName(updateInstrumentModel.getInstrumentName());
        InstrumentModel updatedInstrumentModel = mapToInstrumentModel(instrument);

        cacheService.updateInstrumentInCache(id, updatedInstrumentModel);

        return updatedInstrumentModel;
    }

}
