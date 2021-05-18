package fr.arolla.skocher.traincompany;

public class TripCostCalculator {

    private final int zoneStart;
    private final int zoneStop;

    public TripCostCalculator(int zoneStart, int zoneStop) {
        this.zoneStart = zoneStart;
        this.zoneStop = zoneStop;
    }

    public int getCost() {
        if (zoneStart == 3 && (zoneStop == 1 || zoneStop == 2)) {
            return 280;
        }
        if ((zoneStart == 1 || zoneStart == 2) && zoneStop == 3) {
            return 280;
        }
        if (zoneStart == 4 && (zoneStop == 1 || zoneStop == 2)) {
            return 300;
        }
        if ((zoneStart == 1 || zoneStart == 2) && zoneStop == 4) {
            return 300;
        }
        if (zoneStart == 3 || zoneStart == 4) {
            return 200;
        }

        //Within zone 1 or 2
        return 240;
    }
}
