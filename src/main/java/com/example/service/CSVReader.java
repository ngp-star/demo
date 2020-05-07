package com.example.service;

import com.example.utilities.JSONConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    protected final CsvMapper csvMapper = new CsvMapper();

    public List<JsonNode> load(Class<?> zlass, URL resourceCSVFile) throws IOException {

        CsvSchema csvSchema = csvMapper.typedSchemaFor(zlass).withHeader();
        MappingIterator it = new CsvMapper().readerFor(zlass)
                .with(csvSchema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR))
                .readValues(resourceCSVFile);
        List<JsonNode> listOfJson = new ArrayList<>();
        while (it.hasNext()) {
            listOfJson.add(JSONConverter.getJsonFromString(new Gson().toJson(it.next())));
        }
        return listOfJson;
    }
}
