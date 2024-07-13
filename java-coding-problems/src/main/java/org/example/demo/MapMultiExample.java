package org.example.demo;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class MapMultiExample {

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
        //allocationStatsPerFund();
        //allocationStatsPerFundExcludeDefaultInstrument();
    }

    /* 1. Find total number of instruments per fund, and also collect statistics such as
        count, min, max, sum, and average on allocation amount for each fund*/
    public static void allocationStatsPerFund() {
        List<FundAllocation> fundAllocations = buildFunds();
        Map<String, FundAllocationStats> resultMap = fundAllocations.stream().collect(
                groupingBy(
                        FundAllocation::fundName,
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
        );
        System.out.println("Results: " + resultMap);
        /*
         * {
         * FUND-A=
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
         * FundAllocationStats[
         *   allocationCount=3,
         *   instruments=REVOLVER:TERM:DEFAULT,
         *   allocationStats=
         *       DoubleSummaryStatistics{
         *           count=3,
         *           sum=140000.000000,
         *           min=10000.000000,
         *           average=46666.666667,
         *           max=75000.000000}]}
         *
         * */
    }

    /* 2. Using Map Multi: Find total number of instruments per fund, and also collect statistics such as
            count, min, max, sum, and average on allocation amount for each fund*/
    public static void allocationStatsPerFundExcludeDefaultInstrument() {
        List<FundAllocation> fundAllocations = buildFunds();

        Map<String, FundAllocationStats> resultMap = fundAllocations.stream()
                .<Map.Entry<String, FundAllocation>>mapMulti((fundAllocation, consumer) -> {
                    if (!fundAllocation.instrumentType.equals("DEFAULT")) {
                        consumer.accept(Map.entry(fundAllocation.fundName, fundAllocation));
                    }
                }).collect(
                        groupingBy(
                                Map.Entry::getKey,
                                mapping(
                                        Map.Entry::getValue,
                                        Collectors.collectingAndThen(
                                                toList(),
                                                list -> {
                                                    var instrumentsCount = list.stream().count();
                                                    var instruments = list.stream()
                                                            .map(FundAllocation::instrumentType).collect(joining(":"));
                                                    var allocationStats = list.stream()
                                                            .collect(summarizingDouble(FundAllocation::allocationAmount));
                                                    return new FundAllocationStats(instrumentsCount, instruments, allocationStats);
                                                }
                                        )
                                )
                        )
                );
        System.out.println(resultMap);
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
