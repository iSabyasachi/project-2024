package org.example.domain.vehicle;

import lombok.Data;

@Data
public class Car {
    private final String brand;
    private final String fuel;
    private final int horsePower;
}
