package org.example.domain.financials;

import lombok.Data;
import org.example.domain.financials.enums.InstrumentCategory;
import org.example.memoize.MemoizeSupplier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Data
public class Fund {
    private Long id;
    private String name;
    private Double commitmentAmount;
    private List<Instrument> instruments;

    //private final Supplier<Double> allocationAmount = this::calculateAllocationAmount;
    private final Supplier<Double> allocationAmount = MemoizeSupplier.supplier(this::calculateAllocationAmount);

    public Fund(Long id, String name, List<Instrument> instruments, Double commitmentAmount){
        this.id = id;
        this.name = name;
        this.instruments = instruments;
        this.commitmentAmount = commitmentAmount;
    }

    public Double getAllocationAmount(){
        Double amount = allocationAmount.get();
        System.out.println("get allocation amount..."+amount);
        return amount;
    }

    public Double calculateAllocationAmount(){
        System.out.println("calculating...");
        return allocateAmountToSecurity().stream().mapToDouble(Instrument::getAmountIssued).sum();
    }

    public List<Instrument> allocateAmountToSecurity(){
        return instruments.stream().<Instrument>mapMulti((instrument, mapper) -> {
            Double percentage = instrumentAllocationPercentageRule(instrument.getCategory());
            instrument.setAmountIssued(percentage * commitmentAmount);
            mapper.accept(instrument);
        }).toList();
    }

    private static Double instrumentAllocationPercentageRule(InstrumentCategory category){
        return switch (category){
            case TERM -> .35;
            case REVOLVER -> .25;
            case DELAY_DRAW -> .10;
        };
    }
}
