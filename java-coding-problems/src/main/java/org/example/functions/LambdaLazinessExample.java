package org.example.functions;

import org.example.domain.financials.Fund;

public class LambdaLazinessExample {

    public Double allocateFundImperativeFasion(Fund fund){
        return fund.calculateAllocationAmount();
    }

    public Double allocateFundFunctionalFasion(Fund fund){
        return fund.getAllocationAmount();
    }
}
