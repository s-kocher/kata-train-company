package fr.arolla.skocher.traincompany;

import java.util.List;

public class TripCostCalculator {

    private Trip trip;

    public TripCostCalculator(Trip trip) {
        this.trip = trip;
    }

    public Cost getCost() {
        Station stationStart = trip.getStationStart();
        Station stationEnd  = trip.getStationEnd();

        MultiZonesCostCalculator calculator = new MultiZonesCostCalculator(
            stationStart.getZones(),
            stationEnd.getZones()
        );

        return calculator.getCost();
    }

}
