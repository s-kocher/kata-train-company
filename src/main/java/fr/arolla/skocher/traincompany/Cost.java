package fr.arolla.skocher.traincompany;

public class Cost {

    int costInCents;
    int zoneChargedStart;
    int zoneChargedStop;

    public Cost(int costInCents, int zoneChargedStart, int zoneChargedStop) {
        this.costInCents = costInCents;
        this.zoneChargedStart = zoneChargedStart;
        this.zoneChargedStop = zoneChargedStop;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getZoneChargedStart() {
        return zoneChargedStart;
    }

    public int getZoneChargedStop() {
        return zoneChargedStop;
    }

}
