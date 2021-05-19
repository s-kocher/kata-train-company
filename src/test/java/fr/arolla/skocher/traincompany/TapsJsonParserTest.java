package fr.arolla.skocher.traincompany;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Station;
import fr.arolla.skocher.traincompany.domain.Tap;

public class TapsJsonParserTest {

    @Test
    public void should_extract_empty_list_of_taps_from_an_empty_json() {
        String jsonTaps = "{ }";

        List<Tap> taps = new TapsJsonParser(jsonTaps).parse();

        assertEquals(0, taps.size());
    }

    @Test
    public void should_extract_taps_from_a_json_list_of_taps() {
        String jsonTaps =
            "{" +
            "    \"taps\": [" +
            "        {" +
            "        \"unixTimestamp\": 1572242400," +
            "        \"customerId\": 1," +
            "        \"station\": \"A\"" +
            "        }," +
            "        {" +
            "        \"unixTimestamp\": 1572244200," +
            "        \"customerId\": 1," +
            "        \"station\": \"B\"" +
            "        }," +
            "        {" +
            "        \"unixTimestamp\": 1572282000," +
            "        \"customerId\": 2," +
            "        \"station\": \"C\"" +
            "        }," +
            "        {" +
            "        \"unixTimestamp\": 1572283800," +
            "        \"customerId\": 2," +
            "        \"station\": \"D\"" +
            "        }" +
            "    ]" +
                '}';

        List<Tap> taps = new TapsJsonParser(jsonTaps).parse();

        assertEquals(4, taps.size());
        assertEquals(new Tap(1572242400, 1, Station.A), taps.get(0));
        assertEquals(new Tap(1572244200, 1, Station.B), taps.get(1));
        assertEquals(new Tap(1572282000, 2, Station.C), taps.get(2));
        assertEquals(new Tap(1572283800, 2, Station.D), taps.get(3));
    }

}