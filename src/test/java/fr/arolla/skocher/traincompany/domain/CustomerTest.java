package fr.arolla.skocher.traincompany.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.arolla.skocher.traincompany.billing.TripCostCalculator;

class CustomerTest {

    @Test
    public void should_a_customer_with_no_trip_has_a_total_cost_to_0() {
        Customer customer = new Customer(1);

        int totalCost = customer.getTotalCost();

        assertEquals(0, totalCost);
    }

    @Test
    public void should_a_customer_with_a_single_trip_has_a_total_cost_equal_to_the_trip_s_cost() {
        Customer customer = new Customer(1);
        Trip trip = new Trip(Station.A, Station.B, 1);
        int tripCost = new TripCostCalculator(trip).getCost().getCostInCents();

        customer.addTrip(trip);
        int totalCost = customer.getTotalCost();

        assertEquals(tripCost, totalCost);
    }

    @Test
    public void should_a_customer_with_a_two_trips_has_a_total_cost_equal_to_the_trip_s_costs() {
        Customer customer = new Customer(1);

        Trip trip1 = new Trip(Station.A, Station.B, 1);
        Trip trip2 = new Trip(Station.B, Station.A, 2);

        int tripCost1 = new TripCostCalculator(trip1).getCost().getCostInCents();
        int tripCost2 = new TripCostCalculator(trip2).getCost().getCostInCents();
        int expectedCost = tripCost1 + tripCost2;

        customer.addTrip(trip1);
        customer.addTrip(trip2);
        int totalCost = customer.getTotalCost();

        assertEquals(expectedCost, totalCost);
    }

}