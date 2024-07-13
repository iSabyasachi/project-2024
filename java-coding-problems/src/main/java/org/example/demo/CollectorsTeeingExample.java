package org.example.demo;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

public class CollectorsTeeingExample {
    public record FundAllocation(String fundName, String instrumentType, Double allocationAmount) {
    }

    public static List<FundAllocation> buildFunds() {
        return List.of(
                new FundAllocation("FUND-A", "REVOLVER", 150000.00),
                new FundAllocation("FUND-A", "TERM", 250000.00),
                new FundAllocation("FUND-A", "DELAY-DRAW", 50000.00),
                new FundAllocation("FUND-B", "REVOLVER", 75000.00),
                new FundAllocation("FUND-B", "TERM", 55000.00),
                new FundAllocation("FUND-B", "DEFAULT", 10000.00)
        );
    }

    public static void main(String[] args) {
        findSumAndAvgAllocationAmountPerFund();
    }
    /* 1. Find the sum and average of instrument allocations per fund*/
    public static void findSumAndAvgAllocationAmountPerFund() {
        List<FundAllocation> fundAllocations = buildFunds();
        Map<String, Map<String, Double>> results = fundAllocations.stream().collect(
                groupingBy(
                        FundAllocation::fundName,
                        mapping(
                                FundAllocation::allocationAmount,
                                Collectors.teeing(
                                        Collectors.summingDouble(Double::doubleValue), // First downstream collector
                                        Collectors.averagingDouble(Double::doubleValue), // Second downstream collector
                                        (sum, avg) -> Map.of("Sum", sum, "Average", avg) // Merger function
                                )
                        )
                )
        );
        results.forEach((fundName, stats) -> {
            System.out.println(fundName + ": " + stats);
        });
        /*
        *   FUND-A: {Sum=450000.0, Average=150000.0}
            FUND-B: {Sum=140000.0, Average=46666.666666666664}
        * */
    }
}
