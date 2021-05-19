package fr.arolla.skocher.traincompany;

import java.util.Objects;

public class Trip {

    private final Station stationStart;
    private final Station stationEnd;
    private final int startedJourneyAt;

    public Trip(Station stationStart, Station stationEnd, int startedJourneyAt) {
        this.stationStart = stationStart;
        this.stationEnd = stationEnd;
        this.startedJourneyAt = startedJourneyAt;
    }

    public Station getStationStart() {
        return stationStart;
    }

    public Station getStationEnd() {
        return stationEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return startedJourneyAt == trip.startedJourneyAt && stationStart == trip.stationStart && stationEnd == trip.stationEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationStart, stationEnd, startedJourneyAt);
    }

    @Override
    public String toString() {
        return "Trip{" +
            "stationStart='" + stationStart + '\'' +
            ", stationEnd='" + stationEnd + '\'' +
            ", startedJourneyAt=" + startedJourneyAt +
            '}';
    }
}
