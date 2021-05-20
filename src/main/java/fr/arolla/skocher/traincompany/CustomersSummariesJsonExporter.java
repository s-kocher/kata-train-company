package fr.arolla.skocher.traincompany;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import fr.arolla.skocher.traincompany.billing.TripCostCalculator;
import fr.arolla.skocher.traincompany.domain.Cost;
import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Trip;

public class CustomersSummariesJsonExporter {

    private final List<Customer> customers;
    private final ObjectMapper mapper = getObjectMapper();

    public CustomersSummariesJsonExporter(List<Customer> customers) {
        this.customers = customers;
    }

    public void fillJsonOutputStream(OutputStream os) {
        String json;
        try {
            JsonNode jsonNode = getJsonNode();

            mapper.writerWithDefaultPrettyPrinter().writeValue(os, jsonNode);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error processing customer to json", e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JsonNode getJsonNode() {
        //Wrap customers list in a CustomersSummaries object
        CustomerSummaries customersSummaries = new CustomerSummaries(customers);

        return mapper.valueToTree(customersSummaries);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Customer.class, new CustomerSerializer());
        module.addSerializer(Trip.class, new TripSerializer());
        mapper.registerModule(module);

        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", "\n");

        DefaultPrettyPrinter prettyPrint = new DefaultPrettyPrinter()
            .withArrayIndenter(indenter)
            .withObjectIndenter(indenter);

        mapper.setDefaultPrettyPrinter(prettyPrint);

        return mapper;
    }

    private static class CustomerSummaries {
        private final List<Customer> customerSummaries;

        public CustomerSummaries(List<Customer> customerSummaries) {
            this.customerSummaries = customerSummaries;
        }

        public List<Customer> getCustomerSummaries() {
            return customerSummaries;
        }
    }

    private static class CustomerSerializer extends StdSerializer<Customer> {

        public CustomerSerializer() {
            this(null);
        }

        public CustomerSerializer(Class<Customer> t) {
            super(t);
        }

        @Override
        public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField ("customerId", customer.getId());
            jsonGenerator.writeNumberField ("totalCostInCents", customer.getTotalCost());
            jsonGenerator.writeObjectField("trips", customer.getTrips());
            jsonGenerator.writeEndObject();
        }
    }

    private static class TripSerializer extends StdSerializer<Trip> {

        public TripSerializer() {
            this(null);
        }

        public TripSerializer(Class<Trip> t) {
            super(t);
        }

        @Override
        public void serialize(Trip trip, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField ("stationStart", trip.getStationStart().name());
            jsonGenerator.writeStringField ("stationEnd", trip.getStationEnd().name());
            jsonGenerator.writeNumberField ("startedJourneyAt", trip.getStartedJourneyAt());

            Cost cost = new TripCostCalculator(trip).getCost();
            jsonGenerator.writeNumberField ("costInCents", cost.getCostInCents());
            jsonGenerator.writeNumberField ("zoneFrom", cost.getZoneChargedStart());
            jsonGenerator.writeNumberField ("zoneTo", cost.getZoneChargedEnd());
            jsonGenerator.writeEndObject();
        }
    }
}
