package com.example.financials.response;

import lombok.Data;

@Data
public class InstrumentResponse {
    String instrumentName;
    String instrumentType;
    String ticker;
    String sector;
}
