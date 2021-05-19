package fr.arolla.skocher.traincompany.billing;

import fr.arolla.skocher.traincompany.domain.Cost;
import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Trip;

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
