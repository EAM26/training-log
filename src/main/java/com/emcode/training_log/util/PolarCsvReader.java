package com.emcode.training_log.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class PolarCsvReader {

    public List<String[]> readData(String filePath) {

        Path path = Path.of(filePath);

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {
            return csvReader.readAll();
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
