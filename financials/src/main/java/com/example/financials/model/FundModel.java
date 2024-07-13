package com.example.financials.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class FundModel implements Serializable {
    private String fundName;
    private String fundType;
    private Date creationDate;
    private String manager;
    private List<AllocationModel> allocations;
}
