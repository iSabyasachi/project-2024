package com.example.financials.repository;

import com.example.financials.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
