package com.example.financials.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funds", schema = "financials")
public class Fund implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_id")
    private long fundId;

    @Column(name = "fund_name", nullable = false, length = 100)
    private String fundName;

    @Column(name = "fund_type" , nullable = true, length = 50)
    private String fundType;

    @Temporal(TemporalType.DATE)
    @Column(name = "inception_date" , nullable = true)
    private Date creationDate;

    @Column(name = "manager" , nullable = true, length = 100)
    private String manager;

    @JsonManagedReference(value = "fund-allocations")
    @OneToMany(mappedBy = "fund", fetch = FetchType.EAGER)
    private List<Allocation> allocations;

    /*Getters and Setters*/
    public long getFundId() {
        return fundId;
    }

    public void setFundId(long fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}
