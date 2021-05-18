package fr.arolla.skocher.traincompany;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TripsAssemblerTest {

    @Test
    public void should_return_1_trip_from_2_taps() {
        List<Tap> taps = List.of(
            new Tap(1572242400, "A"),
            new Tap(1572244200, "D")
        );
        TripsAssembler tripsAssembler = new TripsAssembler(taps);

        Trip trip = tripsAssembler.getTrips().get(0);

        assertEquals(new Trip("A", "D", 1572242400), trip);
    }

    @Test
    public void should_return_a_trip_from_2_next_taps() {
        List<Tap> taps = List.of(
            new Tap(1572282000, "D"),
        new Tap(1572283800, "A")
        );
        TripsAssembler tripsAssembler = new TripsAssembler(taps);

        Trip trip = tripsAssembler.getTrips().get(0);

        assertEquals(new Trip("D", "A", 1572282000), trip);
    }

    @Test
    public void should_return_back_and_forth_trips_from_4_taps() {
        List<Tap> taps = List.of(
            new Tap(1572242400, "A"),
            new Tap(1572244200, "D"),
            new Tap(1572282000, "D"),
            new Tap(1572283800, "A")
        );
        TripsAssembler tripsAssembler = new TripsAssembler(taps);

        List<Trip> trips = tripsAssembler.getTrips();

        assertEquals(2, trips.size());
        assertEquals(new Trip("A", "D", 1572242400), trips.get(0));
        assertEquals(new Trip("D", "A", 1572282000), trips.get(1));
    }

    @Test()
    public void should_throw_an_illegal_argument_exception_if_non_pair_number_of_taps() {
        List<Tap> taps = List.of(
            new Tap(1572242400, "A"),
            new Tap(1572244200, "D"),
            new Tap(1572282000, "D")
        );
        TripsAssembler tripsAssembler = new TripsAssembler(taps);

        assertThrows(IllegalArgumentException.class, tripsAssembler::getTrips);
    }


}
