package fr.arolla.skocher.traincompany;

import java.util.ArrayList;
import java.util.List;

public class TripsAssembler {

    private final List<Tap> taps;

    public TripsAssembler(List<Tap> taps) {
        this.taps = taps;
    }

    public List<Trip> getTrips() {
        if (taps.size() % 2 != 0) {
            throw new IllegalArgumentException("Number of taps are not pair");
        }

        List<Trip> trips = new ArrayList<>();
        for (int tapIdx = 0; tapIdx < taps.size(); tapIdx = tapIdx + 2) {
            Tap tapStart = taps.get(tapIdx);
            Tap tapEnd = taps.get(tapIdx + 1);
            trips.add(
                new Trip(tapStart.getStation(), tapEnd.getStation(), tapStart.getUnixTimestamp())
            );
        }

        return trips;
    }

}
