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
    private RedisTemplate<String, Object> redisTemplate;

    private static final String FUND_KEY_PREFIX = "FUND_";
    private static final String INSTRUMENT_KEY_PREFIX = "INSTRUMENT_";
    /* From Postgres Data Store Directly*/
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
        String key = FUND_KEY_PREFIX + id;
        FundModel fundModel = fromCache ? (FundModel)redisTemplate.opsForValue().get(key) : null;
        if(fundModel == null){
            logger.info("Trying to fetch Fund Cached Data. Cache Miss!!!");
            logger.info("Fetch Fund Data From Postgres Database and update Redis Cache!!!");
            Fund fund = fundRepository.findById(id).orElse(null);
            if(Objects.nonNull(fund)){
                fundModel = mapToFundModel(fund);

                logger.info("Update Fund data in Redis Cache!!!");
                redisTemplate.opsForValue().set(key, fundModel);
                return fundModel;
            }
        }

        logger.info("Cache Hit!!! Fetched Fund Data from Cache !!!");
        return fundModel;
    }

    @Transactional(readOnly = true)
    public InstrumentModel findInstrumentById(long id, boolean fromCache){
        String key = INSTRUMENT_KEY_PREFIX + id;
        InstrumentModel instrumentModel = fromCache ? (InstrumentModel)redisTemplate.opsForValue().get(key) : null;
        if(instrumentModel == null){
            logger.info("Trying to fetch Instrument Cached Data. Cache Miss!!!");
            logger.info("Fetch Instrument Data From Postgres Database and update Redis Cache!!!");
            Instrument instrument = instrumentRepository.findById(id).orElse(null);
            if(Objects.nonNull(instrument)){
                instrumentModel = mapToInstrumentModel(instrument);

                logger.info("Update Instrument data in Redis Cache!!!");
                redisTemplate.opsForValue().set(key, instrumentModel);
                return instrumentModel;
            }
        }

        logger.info("Cache Hit!!! Fetched Instrument Data from Cache !!!");
        return instrumentModel;
    }

}
