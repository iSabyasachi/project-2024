package org.example.demo;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorsFilteringExample {
    public record FundAllocation(String fundName, String instrumentType, Double allocationAmount) {
    }

    public record FundAllocationStats(long instrumentsCount, String instruments,
                                      DoubleSummaryStatistics allocationStats) {
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
        //allocationStatsPerFundExcludeDefaultInstrument();
        totalAllocationPerFund();
    }

    /*Calculate total allocation per fund and the total allocation should be at least 200K*/
    public static void totalAllocationPerFund(){
        var funds = buildFunds();
        var result = funds.stream().collect(
                groupingBy(
                        FundAllocation::fundName,
                        collectingAndThen(
                                summingDouble(FundAllocation::allocationAmount),
                                total -> total >= 150000 ? total : 0
                        )
                )
        ).entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));;
        System.out.println(result);
    }

    /* 1. Find total number of instruments per fund, and also collect statistics such as
        count, min, max, sum, and average on allocation amount for each fund
        And Exclude Default Instrument*/
    public static void allocationStatsPerFundExcludeDefaultInstrument() {
        List<FundAllocation> fundAllocations = buildFunds();
        Map<String, FundAllocationStats> resultMap = fundAllocations.stream().collect(
                groupingBy(
                        FundAllocation::fundName,
                        filtering(
                                (fundAllocation) -> !fundAllocation.instrumentType.equals("DEFAULT"),
                                collectingAndThen(
                                        toList(),
                                        list -> {
                                            long instrumentsCount = list.stream().count();
                                            String instruments = list.stream()
                                                    .map(FundAllocation::instrumentType).collect(joining(":"));
                                            DoubleSummaryStatistics allocationStats = list.stream()
                                                    .collect(summarizingDouble(FundAllocation::allocationAmount));
                                            return new FundAllocationStats(instrumentsCount, instruments, allocationStats);
                                        }
                                )
                        )
                )
        );
        System.out.println("Results: " + resultMap);
        /*
         * {FUND-A=
         * FundAllocationStats[
         *   allocationCount=3,
         *   instruments=REVOLVER:TERM:DELAY-DRAW,
         *   allocationStats=
         *       DoubleSummaryStatistics{
         *           count=3,
         *           sum=450000.000000,
         *           min=50000.000000,
         *           average=150000.000000,
         *           max=250000.000000}],
         * FUND-B=
         *   FundAllocationStats[
         *   allocationCount=2,
         *   instruments=REVOLVER:TERM,
         *   allocationStats=
         *       DoubleSummaryStatistics{
         *           count=2,
         *           sum=130000.000000,
         *           min=55000.000000,
         *           average=65000.000000,
         *           max=75000.000000}]}
         * */
    }
}
