package fr.arolla.skocher.traincompany;

public class Tap {

    private final int unixTimestamp;
    private final String station;

    public Tap(int unixTimestamp, String station) {
        this.unixTimestamp = unixTimestamp;
        this.station = station;
    }

    public int getUnixTimestamp() {
        return unixTimestamp;
    }

    public String getStation() {
        return station;
    }
}
