package fr.arolla.skocher.traincompany;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Tap;

public class TapsJsonParser {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonNode rootNode;

    public TapsJsonParser(File fileJsonTaps) {
        try {
            rootNode = mapper.readTree(fileJsonTaps);
        } catch (IOException e) {
            throw new IllegalStateException("Json processing error when parsing taps json file " + fileJsonTaps.getAbsolutePath(), e);
        }
    }

    public TapsJsonParser(String jsonTaps) {
        try {
            rootNode = mapper.readTree(jsonTaps);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Json processing error when parsing taps json content", e);
        }
    }

    public List<Tap> parse() {
        List<Tap> taps = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tapsNode = rootNode.get("taps");
        if (tapsNode == null) {
            return taps;
        }

        Iterator<JsonNode> tapNodesIterator = tapsNode.elements();
        while(tapNodesIterator.hasNext()) {
            JsonNode tapNode = tapNodesIterator.next();
            taps.add(parseTapJson(tapNode));
        }

        return taps;
    }

    private Tap parseTapJson(JsonNode tapNode) {
        int unixTimestamp = tapNode.get("unixTimestamp").asInt();
        long customerId = tapNode.get("customerId").asLong();
        String station = tapNode.get("station").asText();

        return new Tap(unixTimestamp, customerId, Station.valueOf(station));
    }

}
