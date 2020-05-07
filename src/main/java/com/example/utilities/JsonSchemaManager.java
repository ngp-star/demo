package com.example.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.J;

public class JsonSchemaManager {
    private final JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
    private Map<Class<?>, JsonNode> jsonNodeMap = new HashMap<>();

    public void load(Class<?> className, String schema) throws IOException {
        JsonNode schemaFromDisk = JsonLoader.fromURL(this.getClass().getResource(schema));
        jsonNodeMap.put(className, schemaFromDisk);
    }


    public void check(Class<?> className, JsonNode toBeValidate) {

        ProcessingReport report = null;
        try {
            report = validator.validate(jsonNodeMap.get(className), toBeValidate);
            if (!report.isSuccess()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" Oops!! failed JSON validation ");
                stringBuilder.append(":").append("\n");
                List<ProcessingMessage> messages = Lists.newArrayList(report);

                for (int i = 0; i < messages.size(); i++) {
                    stringBuilder.append("- ");
                    stringBuilder.append(messages.get(i).toString());
                    stringBuilder.append((i == (messages.size()) - 1) ? "" : "\r");
                }
                throw new RuntimeException(stringBuilder.toString());
            }
        } catch (ProcessingException e) {
            throw new RuntimeJsonMappingException("ERROR -->" + e.toString());
        }


    }
}
