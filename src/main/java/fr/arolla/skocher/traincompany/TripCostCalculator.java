package fr.arolla.skocher.traincompany;

public class TripCostCalculator {

    private final int zoneStart;
    private final int zoneStop;

    public TripCostCalculator(int zoneStart, int zoneStop) {
        this.zoneStart = zoneStart;
        this.zoneStop = zoneStop;
    }

    public int getCost() {
        int smallestZone = Math.min(zoneStart, zoneStop);
        int highestZone  = Math.max(zoneStart, zoneStop);

        if (isInTwoInnerZones(zoneStart) && isInTwoInnerZones(zoneStop)) {
            return 240;
        }
        if (isInTwoInnerZones(smallestZone) && highestZone == 3) {
            return 280;
        }
        if (isInTwoInnerZones(smallestZone) && highestZone == 4) {
            return 300;
        }

        //Within zone 3 or 4
        return 200;
    }

    private boolean isInTwoInnerZones(int zone) {
        return zone == 1 || zone == 2;
    }

}
