package com.example;

import com.example.model.FloorAccess;
import com.example.model.Transaction;
import com.example.service.CSVReader;
import com.example.utilities.JsonSchemaManager;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public class JSONSchemaReaderApp {

    public static void main(String[] args) throws IOException {

        JsonSchemaManager jsonSchemaManager = new JsonSchemaManager();
        CSVReader csvReader = new CSVReader();
        // Load schema file from resource folder.
        jsonSchemaManager.load(FloorAccess.class, "/schema/schema.json");
        // Load JSON data from file system and convert into JsonNode Object
        List<JsonNode> lists = csvReader.load(FloorAccess.class, JSONSchemaReaderApp.class.getClassLoader().getResource("data.csv"));
        for (int i = 0; i < lists.size(); i++) {
            //validate each JsonNode of Type Transaction to Schema file.
            jsonSchemaManager.check(FloorAccess.class, lists.get(i));
            lists.get(i);
        }

    }
}



