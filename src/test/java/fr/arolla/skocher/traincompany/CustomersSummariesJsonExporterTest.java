package fr.arolla.skocher.traincompany;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Trip;

public class CustomersSummariesJsonExporterTest {

    @Test
    public void should_1_customer_be_exported_as_expected_json() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer(1);
        customer.addTrip(new Trip(Station.A, Station.D, 1572242400));
        customer.addTrip(new Trip(Station.D, Station.A, 1572282000));

        customers.add(customer);

        CustomersSummariesJsonExporter exporter = new CustomersSummariesJsonExporter(customers);

        JsonNode jsonNodeResult = exporter.getJsonNode();


        JsonNode expectedJsonNode = getJsonNodeFromJsonString(
            "{\n" +
            "    \"customerSummaries\": [\n" +
            "    {\n" +
            "        \"customerId\": 1,\n" +
            "        \"totalCostInCents\": 480,\n" +
            "        \"trips\": [\n" +
            "            {\n" +
            "            \"stationStart\": \"A\",\n" +
            "            \"stationEnd\": \"D\",\n" +
            "            \"startedJourneyAt\": 1572242400,\n" +
            "            \"costInCents\": 240,\n" +
            "            \"zoneFrom\": 1,\n" +
            "            \"zoneTo\": 2\n" +
            "            },\n" +
            "            {\n" +
            "            \"stationStart\": \"D\",\n" +
            "            \"stationEnd\": \"A\",\n" +
            "            \"startedJourneyAt\": 1572282000,\n" +
            "            \"costInCents\": 240,\n" +
            "            \"zoneFrom\": 2,\n" +
            "            \"zoneTo\": 1\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "    ]\n" +
            "}"
        );

        //Compare 2 JsonNode to not be dependant of small differences like spaces, end of line sequence
        //between the declared expected close from the specification
        //and the Jackson Pretty Printer settings, hard to align easily
        //Finally back to String comparison because the 2 instances are not equal
        Assertions.assertEquals(expectedJsonNode.toPrettyString(), jsonNodeResult.toPrettyString());
    }

    private JsonNode getJsonNodeFromJsonString(String jsonContent) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readTree(jsonContent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
