package fr.arolla.skocher.traincompany;

import java.util.Objects;

public class Trip {

    private final Station stationStart;
    private final Station stationStop;
    private final int startedJourneyAt;

    public Trip(Station stationStart, Station stationStop, int startedJourneyAt) {
        this.stationStart = stationStart;
        this.stationStop = stationStop;
        this.startedJourneyAt = startedJourneyAt;
    }

    public Station getStationStart() {
        return stationStart;
    }

    public Station getStationStop() {
        return stationStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return startedJourneyAt == trip.startedJourneyAt && stationStart == trip.stationStart && stationStop == trip.stationStop;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationStart, stationStop, startedJourneyAt);
    }

    @Override
    public String toString() {
        return "Trip{" +
            "stationStart='" + stationStart + '\'' +
            ", stationEnd='" + stationStop + '\'' +
            ", startedJourneyAt=" + startedJourneyAt +
            '}';
    }
}
