package fr.arolla.skocher.traincompany;

import java.util.Objects;

public class Trip {

    private final String stationStart;
    private final String stationEnd;
    private final int startedJourneyAt;

    public Trip(String stationStart, String stationEnd, int startedJourneyAt) {
        this.stationStart = stationStart;
        this.stationEnd = stationEnd;
        this.startedJourneyAt = startedJourneyAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return startedJourneyAt == trip.startedJourneyAt && stationStart.equals(trip.stationStart) && stationEnd.equals(trip.stationEnd);
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
