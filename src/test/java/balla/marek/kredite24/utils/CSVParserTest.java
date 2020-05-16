package balla.marek.kredite24.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CSVParserTest {

    @Test
    public void shouldParseBookCSV() throws IOException {
        InputStream csv = new ClassPathResource("books.csv").getInputStream();
        CSVParser parser = new CSVParser();
        List<Map<String, String>> records = parser.parse(csv, ";");
        Assertions.assertEquals(3, records.size());
        Assertions.assertEquals(5, records.get(0).keySet().size());
        Assertions.assertEquals("b1", records.get(0).get("id"));
    }

    @Test
    public void shouldParseBookViewsCSV() throws IOException {
        InputStream csv = new ClassPathResource("books-views.csv").getInputStream();
        CSVParser parser = new CSVParser();
        List<Map<String, String>> records = parser.parse(csv, ";");
        Assertions.assertEquals(6, records.size());
        Assertions.assertEquals(2, records.get(0).keySet().size());
        Assertions.assertEquals("\"test1@check24.de\"", records.get(0).get("user"));
    }
}
