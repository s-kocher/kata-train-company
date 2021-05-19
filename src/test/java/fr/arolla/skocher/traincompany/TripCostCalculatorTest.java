package fr.arolla.skocher.traincompany;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TripCostCalculatorTest {

    @Test
    public void should_trip_from_station_a_to_station_b_cost_240() {
        Trip trip = new Trip(Station.A, Station.B, 1);

        int cost = new TripCostCalculator(trip).getCost();

        assertEquals(240, cost);
    }

    @Test
    public void should_trip_from_station_a_to_station_i_cost_300() {
        Trip trip = new Trip(Station.A, Station.I, 1);

        int cost = new TripCostCalculator(trip).getCost();

        assertEquals(300, cost);
    }

}