package org.example.demo;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsFlatMappingExample {
    public record Instrument(String name, Double allocationAmount){}
    public record Fund(String name, Set<Instrument> instruments) {}

    public static List<Fund> buildFunds() {
        return List.of(
                new Fund("Fund-A",
                        Set.of(new Instrument("Instrument-A" , 150000.00),
                                new Instrument("Instrument-B", 150000.00),
                                new Instrument("Instrument-C", 150000.00))),
                new Fund("Fund-B",
                        Set.of(new Instrument("Instrument-D", 75000.00),
                                new Instrument("Instrument-E", 55000.00))));
    }

    public static void main(String[] args) {
        flatListOfAllInstrumentsForAllFunds();
    }
    /*Create flat list of all instruments across all funds*/
    public static void flatListOfAllInstrumentsForAllFunds(){
        List<Fund> funds = buildFunds();

        List<Instrument> instruments = funds.stream().collect(
                Collectors.flatMapping(
                        fund -> fund.instruments().stream(),Collectors.toList()
                )
        );
        System.out.println(instruments);
        /*
        * [Instrument[
        *   name=Instrument-C,
        *   allocationAmount=150000.0],
        * Instrument[
        *   name=Instrument-B,
        *   allocationAmount=150000.0],
        * Instrument[
        *   name=Instrument-A,
        *   allocationAmount=150000.0],
        * Instrument[
        *   name=Instrument-D,
        *   allocationAmount=75000.0],
        * Instrument[
        *   name=Instrument-E,
        *   allocationAmount=55000.0]]
         * */
    }
}
