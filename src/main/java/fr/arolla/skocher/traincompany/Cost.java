package fr.arolla.skocher.traincompany;

public class Cost {

    int costInCents;
    int zoneChargedStart;
    int zoneChargedEnd;

    public Cost(int costInCents, int zoneChargedStart, int zoneChargedEnd) {
        this.costInCents = costInCents;
        this.zoneChargedStart = zoneChargedStart;
        this.zoneChargedEnd = zoneChargedEnd;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getZoneChargedStart() {
        return zoneChargedStart;
    }

    public int getZoneChargedEnd() {
        return zoneChargedEnd;
    }

}
