package fr.arolla.skocher.traincompany;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ZoneCostCalculatorTest {

    private static Stream<Arguments> getZoneBillingRules() {
        return Stream.of(
            Arguments.of(1, 1, 240),
            Arguments.of(1, 2, 240),
            Arguments.of(2, 1, 240),
            Arguments.of(2, 2, 240),

            Arguments.of(3, 3, 200),
            Arguments.of(3, 4, 200),
            Arguments.of(4, 3, 200),
            Arguments.of(4, 4, 200),

            Arguments.of(1, 3, 280),
            Arguments.of(2, 3, 280),
            Arguments.of(3, 1, 280),
            Arguments.of(3, 2, 280),

            Arguments.of(1, 4, 300),
            Arguments.of(2, 4, 300),
            Arguments.of(4, 1, 300),
            Arguments.of(4, 2, 300)

        );
    }

    @ParameterizedTest(name="Zone start : {0} to zone end {1} => Cost : {2}")
    @MethodSource(value="getZoneBillingRules")
    public void should_trip_from_a_zone_to_another_zone_has_expected_cost(int zoneStart, int zoneEnd, int expectedCost) {
        ZoneCostCalculator calculator = new ZoneCostCalculator(zoneStart, zoneEnd);

        int cost = calculator.getCost();

        Assertions.assertEquals(expectedCost, cost);
    }

}
