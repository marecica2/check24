package balla.marek.kredite24.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReader<T> {
    public List<T> readCSV(InputStream csvStream, Function<Map<String, String>, T> mapper) throws IOException {
        CSVParser reader = new CSVParser();
        List<Map<String, String>> records = reader.parse(csvStream, ";");
        return records.stream().map(mapper).collect(Collectors.toList());
    }
}
