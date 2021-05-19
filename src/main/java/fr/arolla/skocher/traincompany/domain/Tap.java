package fr.arolla.skocher.traincompany.domain;

public class Tap {

    private final int unixTimestamp;
    private final long customerId;
    private final Station station;

    public Tap(int unixTimestamp, long customerId, Station station) {
        this.unixTimestamp = unixTimestamp;
        this.customerId = customerId;
        this.station = station;
    }

    public int getUnixTimestamp() {
        return unixTimestamp;
    }

    public Station getStation() {
        return station;
    }
}
