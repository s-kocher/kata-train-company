package fr.arolla.skocher.traincompany.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Station {

    A(1),
    B(1),
    C(2, 3),
    D(2),
    E(2, 3),
    F(3, 4),
    G(4),
    H(4),
    I(4);

    private final List<Integer> zones;

    Station(int ...zones) {
        this.zones = Arrays.stream(zones)
            .boxed()
            .collect(Collectors.toList());
    }

    public List<Integer> getZones() {
        return zones;
    }

}
