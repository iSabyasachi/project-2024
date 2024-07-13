package com.example.financials.response;

import lombok.Data;

import java.util.Date;

@Data
public class FundResponse {
    private String fundName;
    private String fundType;
    private Date creationDate;
    private String manager;
}
