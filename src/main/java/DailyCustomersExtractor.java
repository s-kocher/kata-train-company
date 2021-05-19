import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.arolla.skocher.traincompany.TripsAssembler;
import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Tap;
import fr.arolla.skocher.traincompany.domain.Trip;

public class DailyCustomersExtractor {

    private final List<Tap> taps;

    public DailyCustomersExtractor(List<Tap> taps) {
        this.taps = taps;
    }

    public List<Customer> getCustomers() {
        Map<Long, List<Tap>> customersTaps = taps.stream()
            .collect(
                Collectors.groupingBy((Tap::getCustomerId))
            );

        List<Customer> customers = new ArrayList<>();
        for (Map.Entry<Long, List<Tap>> customerTaps : customersTaps.entrySet()) {
            List<Trip> trips = new TripsAssembler(customerTaps.getValue()).getTrips();
            Customer customer = new Customer(customerTaps.getKey(), trips);

            customers.add(customer);
        }

        return customers;
    }

}
