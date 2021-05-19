package fr.arolla.skocher.traincompany;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {

    @Test
    public void should_station_a_be_in_zone_1() {
        Station station = Station.A;

        assertEquals(1, station.getZones().size());
        assertEquals(1, station.getZones().get(0));
    }

    @Test
    public void should_station_c_be_in_zones_2_and_3() {
        Station station = Station.C;

        assertEquals(2, station.getZones().size());
        assertEquals(2, station.getZones().get(0));
        assertEquals(3, station.getZones().get(1));
    }

}