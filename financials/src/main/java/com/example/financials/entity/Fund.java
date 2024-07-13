package com.example.financials.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "funds", schema = "financials")
@ToString(exclude = "allocations")
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

    @JsonIgnore
    @OneToMany(mappedBy = "fund")
    private List<Allocation> allocations;
}
