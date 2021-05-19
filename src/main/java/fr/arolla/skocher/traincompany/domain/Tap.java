package fr.arolla.skocher.traincompany.domain;

public class Tap {

    private final int unixTimestamp;
    private final Station station;

    public Tap(int unixTimestamp, Station station) {
        this.unixTimestamp = unixTimestamp;
        this.station = station;
    }

    public int getUnixTimestamp() {
        return unixTimestamp;
    }

    public Station getStation() {
        return station;
    }
}
