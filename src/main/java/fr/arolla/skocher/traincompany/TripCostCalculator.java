package fr.arolla.skocher.traincompany;

import java.util.List;

public class TripCostCalculator {

    private Trip trip;

    public TripCostCalculator(Trip trip) {
        this.trip = trip;
    }

    public Cost getCost() {
        Station stationStart = trip.getStationStart();
        Station stationStop  = trip.getStationStop();

        MultiZonesCostCalculator calculator = new MultiZonesCostCalculator(
            stationStart.getZones(),
            stationStop.getZones()
        );

        return calculator.getCost();
    }

}
