package fr.arolla.skocher.traincompany;

import java.util.List;

public class MultiZonesCostCalculator {

    private final List<Integer> zonesStart;
    private final List<Integer> zonesEnd;

    public MultiZonesCostCalculator(List<Integer> zonesStart, List<Integer> zonesEnd) {
        this.zonesStart = zonesStart;
        this.zonesEnd = zonesEnd;
    }

    public Cost getCost() {
        int smallestCostInCents = -1;

        Cost cost = null;

        for (int zoneStart : zonesStart) {
            for (int zoneEnd : zonesEnd) {
                ZoneCostCalculator singleZoneCalculator = new ZoneCostCalculator(zoneStart, zoneEnd);
                int currentCostInCents = singleZoneCalculator.getCost();

                if (smallestCostInCents == -1 || smallestCostInCents > currentCostInCents) {
                    smallestCostInCents = currentCostInCents;
                    cost = new Cost(currentCostInCents, zoneStart, zoneEnd);
                }
            }
        }

        return cost;
    }

}
