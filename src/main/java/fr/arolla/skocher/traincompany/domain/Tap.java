package fr.arolla.skocher.traincompany.domain;

import java.util.Objects;

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

    public long getCustomerId() {
        return customerId;
    }

    public Station getStation() {
        return station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tap tap = (Tap) o;
        return unixTimestamp == tap.unixTimestamp && customerId == tap.customerId && station == tap.station;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unixTimestamp, customerId, station);
    }

    @Override
    public String toString() {
        return "Tap{" +
            "unixTimestamp=" + unixTimestamp +
            ", customerId=" + customerId +
            ", station=" + station +
            '}';
    }
}
