package com.example.financials.service;

import com.example.financials.entity.Fund;
import com.example.financials.model.FundModel;
import com.example.financials.model.InstrumentModel;
import com.example.financials.repository.FundRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.financials.mapper.EntityMapper.mapToFundModel;

@Service
public class CacheService {
    static Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    FundRepository fundRepository;

    private static final String FUND_KEY_PREFIX = "FUND_";
    private static final String INSTRUMENT_KEY_PREFIX = "INSTRUMENT_";
    private static final long CACHE_EXPIRATION_TIME = 60; // seconds
    private static final long REFRESH_TIME = 50; // seconds

    /*Refresh-Ahead Pattern :
    * The Refresh-Ahead Pattern in caching is a strategy used to keep cached data up-to-date by proactively refreshing
    * or updating the cache entries before they expire.
     * */
    @PostConstruct
    public void init(){
        logger.info("CacheService.init() is called!!!");
        //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //scheduler.scheduleAtFixedRate(this::refreshFundCache, 0, 10, TimeUnit.SECONDS);
    }

    private void refreshFundCache(){
        logger.info("Refresh Fund Cache initiated!!!");

        for(String key : redisTemplate.keys(FUND_KEY_PREFIX + "*")){
            Long id = Long.valueOf(key.replace(FUND_KEY_PREFIX,  ""));
            Long expirationTime = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if(expirationTime != null && expirationTime <= REFRESH_TIME){
                Fund fund = fundRepository.findById(id).orElse(null);
                if(fund != null){
                    updateFundInCache(id, mapToFundModel(fund));
                }
            }
        }
    }

    public FundModel getFundByIdFromCache(long id){
        String key = FUND_KEY_PREFIX + id;
        FundModel fundModel = (FundModel)redisTemplate.opsForValue().get(key);

        if(fundModel == null){
            logger.info("Trying to fetch Fund Cached Data. Cache Miss!!!");
        }

        logger.info("Cache Hit!!! Fetched Fund Data from Cache !!!");
        return fundModel;
    }

    public FundModel updateFundInCache(long id, FundModel fundModel){
        logger.info("Updated Fund data in Redis Cache!!!");

        String key = FUND_KEY_PREFIX + id;
        redisTemplate.opsForValue().set(key, fundModel, CACHE_EXPIRATION_TIME, TimeUnit.SECONDS);

        return fundModel;
    }

    public InstrumentModel getInstrumentByIdFromCache(long id){
        String key = INSTRUMENT_KEY_PREFIX + id;
        InstrumentModel instrumentModel = (InstrumentModel)redisTemplate.opsForValue().get(key);

        if(instrumentModel == null){
            logger.info("Trying to fetch Instrument Cached Data. Cache Miss!!!");
        }

        logger.info("Cache Hit!!! Fetched Instrument Data from Cache !!!");
        return instrumentModel;
    }

    public InstrumentModel updateInstrumentInCache(long id, InstrumentModel instrumentModel){
        logger.info("Update Instrument data in Redis Cache!!!");

        String key = INSTRUMENT_KEY_PREFIX + id;
        redisTemplate.opsForValue().set(key, instrumentModel);

        return instrumentModel;
    }
}
