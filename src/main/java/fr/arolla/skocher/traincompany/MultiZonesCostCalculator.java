package fr.arolla.skocher.traincompany;

import java.util.List;

public class MultiZonesCostCalculator {

    private final List<Integer> zonesStart;
    private final List<Integer> zonesStop;

    public MultiZonesCostCalculator(List<Integer> zonesStart, List<Integer> zonesStop) {
        this.zonesStart = zonesStart;
        this.zonesStop = zonesStop;
    }

    public int getCost() {
        int smallerCost = -1;

        for (int zoneStart : zonesStart) {
            for (int zoneStop : zonesStop) {
                ZoneCostCalculator singleZoneCalculator = new ZoneCostCalculator(zoneStart, zoneStop);
                int cost = singleZoneCalculator.getCost();

                if (smallerCost == -1) {
                    smallerCost = cost;
                } else if (smallerCost > cost) {
                    smallerCost = cost;
                }
            }
        }

        return smallerCost;
    }

}
