package com.example.financials.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/*Acts as DTO.
Use Data Transfer Objects (DTOs) to detach entities from the session and avoid lazy loading Or Serialization issue.*/
@Data
public class FundModel implements Serializable {
    private String fundName;
    private String fundType;
    private Date creationDate;
    private String manager;
    private List<AllocationModel> allocations;
}
