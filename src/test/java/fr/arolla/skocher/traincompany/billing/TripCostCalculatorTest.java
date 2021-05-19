package fr.arolla.skocher.traincompany.billing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.arolla.skocher.traincompany.billing.TripCostCalculator;
import fr.arolla.skocher.traincompany.domain.Cost;
import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Trip;

class TripCostCalculatorTest {

    @Test
    public void should_trip_from_station_a_to_station_b_cost_240() {
        Trip trip = new Trip(Station.A, Station.B, 1);

        Cost cost = new TripCostCalculator(trip).getCost();

        assertEquals(240, cost.getCostInCents());
    }

    @Test
    public void should_trip_from_station_a_to_station_i_cost_300() {
        Trip trip = new Trip(Station.A, Station.I, 1);

        Cost cost = new TripCostCalculator(trip).getCost();

        assertEquals(300, cost.getCostInCents());
    }

}