package fr.arolla.skocher.traincompany.billing;

public class ZoneCostCalculator {

    private final int zoneStart;
    private final int zoneEnd;

    public ZoneCostCalculator(int zoneStart, int zoneEnd) {
        this.zoneStart = zoneStart;
        this.zoneEnd = zoneEnd;
    }

    public int getCost() {
        int smallestZone = Math.min(zoneStart, zoneEnd);
        int highestZone  = Math.max(zoneStart, zoneEnd);

        if (isInTwoInnerZones(zoneStart) && isInTwoInnerZones(zoneEnd)) {
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
