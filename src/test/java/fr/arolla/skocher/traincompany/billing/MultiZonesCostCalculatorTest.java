package fr.arolla.skocher.traincompany.billing;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import fr.arolla.skocher.traincompany.billing.MultiZonesCostCalculator;
import fr.arolla.skocher.traincompany.domain.Cost;

public class MultiZonesCostCalculatorTest {

    private static Stream<Arguments> getZoneBillingRules() {
        return Stream.of(
            Arguments.of(List.of(1), List.of(1), 240, 1, 1),
            Arguments.of(List.of(1), List.of(2), 240, 1, 2),
            Arguments.of(List.of(1,2), List.of(2), 240, 1, 2),
            Arguments.of(List.of(3), List.of(3), 200, 3, 3),
            Arguments.of(List.of(2,3), List.of(2), 240, 2, 2),
            Arguments.of(List.of(3,2), List.of(2), 240, 2, 2),
            Arguments.of(List.of(2,3), List.of(3), 200, 3, 3),
            Arguments.of(List.of(3,2), List.of(3), 200, 3, 3),
            Arguments.of(List.of(3,4), List.of(4), 200, 3, 4),
            Arguments.of(List.of(3,4), List.of(3), 200, 3, 3),
            Arguments.of(List.of(3,4), List.of(1), 280, 3, 1),
            Arguments.of(List.of(3,4), List.of(1,2), 280, 3, 1),
            Arguments.of(List.of(4), List.of(1,2), 300, 4, 1)
        );
    }

    @ParameterizedTest(name="Multi zones start : {0} to multi zones end {1} => Cost : {2}")
    @MethodSource(value="getZoneBillingRules")
    public void should_trip_from_multi_zone_station_to_another_multi_zone_station_has_expected_cost(List<Integer> zonesStart, List<Integer> zonesEnd, int expectedCost) {
        MultiZonesCostCalculator calculator = new MultiZonesCostCalculator(zonesStart, zonesEnd);

        Cost cost = calculator.getCost();

        Assertions.assertEquals(expectedCost, cost.getCostInCents());
    }

    @ParameterizedTest(name="Multi zones start : {0} to multi zones end {1} => Charged zones : {3}-{4}")
    @MethodSource(value="getZoneBillingRules")
    public void should_trip_from_multi_zone_station_to_another_multi_zone_station_has_expected_charged_zones(List<Integer> zonesStart, List<Integer> zonesEnd, int expectedCost, int expectedChargedZoneStart, int expectedChargedZoneEnd) {
        MultiZonesCostCalculator calculator = new MultiZonesCostCalculator(zonesStart, zonesEnd);

        Cost cost = calculator.getCost();

        Assertions.assertEquals(expectedChargedZoneStart, cost.getZoneChargedStart());
        Assertions.assertEquals(expectedChargedZoneEnd, cost.getZoneChargedEnd());
    }


}