package org.example.domain.vehicle;

import lombok.Data;

import java.util.Comparator;

@Data
public class BicycleComparator implements Comparator<Bicycle> {
    @Override
    public int compare(Bicycle o1, Bicycle o2) {
        return Integer.compare(o1.getFrameSize(), o2.getFrameSize());
    }
}
