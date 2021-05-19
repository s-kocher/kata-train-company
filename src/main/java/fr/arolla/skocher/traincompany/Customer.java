package fr.arolla.skocher.traincompany;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    final long id;
    final List<Trip> trips = new ArrayList<>();

    public Customer(long id) {
        this.id = id;
    }

    public int getTotalCost() {
        int totalCost = 0;

        for (Trip trip : trips) {
            TripCostCalculator calculator = new TripCostCalculator(trip);
            int costTrip = calculator.getCost();
            totalCost += costTrip;
        }

        return totalCost;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

}
