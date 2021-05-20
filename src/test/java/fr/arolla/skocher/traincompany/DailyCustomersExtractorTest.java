package fr.arolla.skocher.traincompany;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.arolla.skocher.traincompany.DailyCustomersExtractor;
import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Tap;
import fr.arolla.skocher.traincompany.domain.Trip;

class DailyCustomersExtractorTest {


    @Test
    public void should_several_taps_from_same_customer_return_a_customer_list_with_the_single_same_customer() {
        List<Tap> taps = new ArrayList<>();
        taps.add(new Tap(1572242400, 1, Station.A));
        taps.add(new Tap(1572244200, 1, Station.B));

        List<Customer> customers = new DailyCustomersExtractor(taps).getCustomers();

        Customer customer = new Customer(1);
        customer.addTrip(new Trip(Station.A, Station.B, 1572242400));


        assertEquals(1, customers.size());
        assertEquals(customer, customers.get(0));
    }

    @Test
    public void should_several_taps_from_two_customer_return_a_customer_list_with_the_two_customer() {
        List<Tap> taps = new ArrayList<>();
        taps.add(new Tap(1572242400, 1, Station.A));
        taps.add(new Tap(1572244200, 1, Station.B));
        taps.add(new Tap(1572242401, 2, Station.C));
        taps.add(new Tap(1572244201, 2, Station.D));

        List<Customer> customers = new DailyCustomersExtractor(taps).getCustomers();

        Customer customer1 = new Customer(1);
        customer1.addTrip(new Trip(Station.A, Station.B, 1572242400));

        Customer customer2 = new Customer(2);
        customer2.addTrip(new Trip(Station.C, Station.D, 1572242401));

        assertEquals(2, customers.size());
        assertTrue(customers.contains(customer1));
        assertTrue(customers.contains(customer2));
    }

}