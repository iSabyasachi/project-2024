package org.example.domain.vehicle;

import lombok.Data;

@Data
public class Submersible implements Vehicle{
    private final String type;
    private final int maxDepth;
}
