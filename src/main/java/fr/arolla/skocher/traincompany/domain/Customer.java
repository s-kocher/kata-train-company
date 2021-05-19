package fr.arolla.skocher.traincompany.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.arolla.skocher.traincompany.billing.TripCostCalculator;

public class Customer {

    final long id;
    List<Trip> trips = new ArrayList<>();

    public Customer(long id) {
        this.id = id;
    }

    public Customer(long id, List<Trip> trips) {
        this(id);
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public long getId() {
        return id;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public int getTotalCost() {
        int totalCost = 0;

        for (Trip trip : trips) {
            TripCostCalculator calculator = new TripCostCalculator(trip);
            int costTrip = calculator.getCost().getCostInCents();
            totalCost += costTrip;
        }

        return totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && trips.equals(customer.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trips);
    }
}
