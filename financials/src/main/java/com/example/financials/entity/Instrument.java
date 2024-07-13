package com.example.financials.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "instruments", schema = "financials")
@ToString(exclude = "allocations")
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private Long instrumentId;

    @Column(name = "instrument_name", nullable = false, length = 100)
    private String instrumentName;

    @Column(name = "instrument_type", length = 50)
    private String instrumentType;

    @Column(name = "ticker", length = 50)
    private String ticker;

    @Column(name = "sector", length = 50)
    private String sector;

    @JsonIgnore
    @OneToMany(mappedBy = "instrument")
    private List<Allocation> allocations;
}
