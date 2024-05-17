package org.example.domain.vehicle;

import lombok.Data;

@Data
public class Bicycle {
    private final String brand;
    private final Integer frameSize;

    public Bicycle(String brand){
        this.brand = brand;
        this.frameSize = 0;
    }

    public Bicycle(String brand, Integer frameSize){
        this.brand = brand;
        this.frameSize = frameSize;
    }
}
