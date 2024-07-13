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

import java.util.Objects;

import static com.example.financials.mapper.EntityMapper.*;

@Service
public class FundAllocationService {
    static Logger logger = LoggerFactory.getLogger(FundAllocationService.class);

    @Autowired
    FundRepository fundRepository;

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String FUND_KEY_PREFIX = "FUND_";
    private static final String INSTRUMENT_KEY_PREFIX = "INSTRUMENT_";
    /* From Postgres Data Store Directly*/
    public FundModel getFundByIdFromPostgres(long id){
        Fund fund = fundRepository.findById(id).orElse(null);
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
    public FundModel findFundById(long id){
        String key = FUND_KEY_PREFIX + id;
        Fund fund = (Fund)redisTemplate.opsForValue().get(key);
        if(fund == null){
            logger.info("Cache Miss!!! Trying fetch Fund Cached Data.");
            fund = fundRepository.findById(id).orElse(null);
            if(fund != null){
                logger.info("From Postgres Database, Fund Data -> ", fund);
                redisTemplate.opsForValue().set(key, fund);
            }
        }else{
            logger.info("Cache Hit!!! Fund Cached Data -> ", fund);
        }

        if(Objects.nonNull(fund)){
            FundModel fundModel = mapToFundModel(fund);
            return fundModel;
        }

        return null;
    }

    public InstrumentModel findInstrumentById(long id){
        String key = INSTRUMENT_KEY_PREFIX + id;
        Instrument instrument = (Instrument) redisTemplate.opsForValue().get(key);
        if(instrument == null){
            logger.info("Cache Miss!!! Trying fetch Instrument Cached Data.");
            instrument = instrumentRepository.findById(id).orElse(null);
            if(instrument != null){
                logger.info("From Postgres Database, Instrument Data -> ", instrument);
                redisTemplate.opsForValue().set(key, instrument);
            }
        }else{
            logger.info("Cache Hit!!! Instrument Cached Data -> ", instrument);
        }
        if(Objects.nonNull(instrument)){
            InstrumentModel instrumentModel = new InstrumentModel();
            instrumentModel.setInstrumentName(instrument.getInstrumentName());
            instrumentModel.setInstrumentType(instrument.getInstrumentType());
            instrumentModel.setTicker(instrument.getTicker());
            instrumentModel.setSector(instrument.getSector());

            return instrumentModel;
        }

        return null;
    }

}
