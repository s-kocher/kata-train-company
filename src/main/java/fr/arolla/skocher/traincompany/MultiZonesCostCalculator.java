package fr.arolla.skocher.traincompany;

import java.util.List;

public class MultiZonesCostCalculator {

    private final List<Integer> zonesStart;
    private final List<Integer> zonesStop;

    public MultiZonesCostCalculator(List<Integer> zonesStart, List<Integer> zonesStop) {
        this.zonesStart = zonesStart;
        this.zonesStop = zonesStop;
    }

    public Cost getCost() {
        int smallestCostInCents = -1;

        Cost cost = null;

        for (int zoneStart : zonesStart) {
            for (int zoneStop : zonesStop) {
                ZoneCostCalculator singleZoneCalculator = new ZoneCostCalculator(zoneStart, zoneStop);
                int currentCostInCents = singleZoneCalculator.getCost();

                if (smallestCostInCents == -1 || smallestCostInCents > currentCostInCents) {
                    smallestCostInCents = currentCostInCents;
                    cost = new Cost(currentCostInCents, zoneStart, zoneStop);
                }
            }
        }

        return cost;
    }

}
