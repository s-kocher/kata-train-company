package fr.arolla.skocher.traincompany;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MultiZonesCostCalculatorTest {

    private static Stream<Arguments> getZoneBillingRules() {
        return Stream.of(
            Arguments.of(List.of(1), List.of(1), 240),
            Arguments.of(List.of(1), List.of(2), 240),
            Arguments.of(List.of(1,2), List.of(2), 240),
            Arguments.of(List.of(3), List.of(3), 200),
            Arguments.of(List.of(2,3), List.of(2), 240),
            Arguments.of(List.of(3,2), List.of(2), 240),
            Arguments.of(List.of(2,3), List.of(3), 200),
            Arguments.of(List.of(3,2), List.of(3), 200),
            Arguments.of(List.of(3,4), List.of(4), 200),
            Arguments.of(List.of(3,4), List.of(3), 200),
            Arguments.of(List.of(3,4), List.of(1), 280),
            Arguments.of(List.of(3,4), List.of(1,2), 280),
            Arguments.of(List.of(4), List.of(1,2), 300)
        );
    }

    @ParameterizedTest(name="Multi zones start : {0} to multi zones stop {1} => Cost : {2}")
    @MethodSource(value="getZoneBillingRules")
    public void should_trip_from_multi_zone_station_to_another_multi_zone_station_has_expected_cost(List<Integer> zonesStart, List<Integer> zonesStop, int expectedCost) {
        MultiZonesCostCalculator calculator = new MultiZonesCostCalculator(zonesStart, zonesStop);

        int cost = calculator.getCost();

        Assertions.assertEquals(expectedCost, cost);
    }


}