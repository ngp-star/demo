package com.example.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;

public class JSONConverter {

    protected static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = ThreadLocal.withInitial(() -> new ObjectMapper());

    /**
     * Convert String as JSON to JsonNode.
     *
     * @param payload
     * @return
     */
    public static JsonNode getJsonFromString(String payload) {
        try {
            return OBJECT_MAPPER_THREAD_LOCAL.get().readTree(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert JsonNode Object to Map
     *
     * @param payload
     * @return
     */
    public static Map getMapFromJson(JsonNode payload) {
        try {
            return OBJECT_MAPPER_THREAD_LOCAL.get().readerFor(Map.class).readValue(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert Map Object to JsonNode.
     *
     * @param payload
     * @return
     */
    public static JsonNode getJsonFromMap(Map payload) {
        try {
            return getJsonFromString(OBJECT_MAPPER_THREAD_LOCAL.get().writeValueAsString(payload));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a Empty Object Node. i.e {}
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return OBJECT_MAPPER_THREAD_LOCAL.get().createObjectNode();
    }

    /**
     * Create a Empty Array Json Object i.e [{},{}]
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return OBJECT_MAPPER_THREAD_LOCAL.get().createArrayNode();
    }

    /**
     * Remove particular key elements from JsonNode.
     *
     * @param payload
     * @param key
     * @return
     */
    public static JsonNode remove(JsonNode payload, String key) {
        ((ObjectNode) payload).remove(key);
        return payload;
    }

    public static void main(String[] args) {
        String payload = "{\"id\":\"1001\", \"amount\":\"10000045.999\", \"currency\":\"EUR\"}";
        System.out.println(" Payload String  >> " + payload);
        JsonNode jsonNode = getJsonFromString(payload);
        System.out.println(" Convert String Payload to JsonNode  >> " + payload);
        Map map = getMapFromJson(getJsonFromString(payload));
        System.out.println(" GET Map Object from Payload String >> " + map);
        System.out.println(" Before Removing 'amount' from payload >> " + jsonNode);
        remove(jsonNode, "amount");
        System.out.println(" After Removing 'amount' from payload >> " + jsonNode);
        System.out.println(" Convert Map to JSON Node Object  >>" + getJsonFromMap(map));

        System.out.println(" Employee JsonNode >>" + createEmployeeJson());
        System.out.println(" Convert Employee Json to Map Object >>" + getMapFromJson(createEmployeeJson()));
    }

    /**
     * {
     * "id":"123",
     * "name":"Rahul Kumar",
     * "address":{
     *      "street":"Kalkaji",
     *      "city":"Delhi",
     *      "zipcode":"110020"
     * },
     * "phoneNumbers":[
     *      "9923456469",
     *      "9886786786"
     * ],
     * "role":"Developer"
     * }
     *
     * @return
     */
    private static JsonNode createEmployeeJson() {
        ObjectNode employeeJsonNode = createObjectNode();
        ObjectNode addressNode = createObjectNode();
        ArrayNode phoneList = createArrayNode();
        addressNode.put("street", "Kalkaji");
        addressNode.put("city", "Delhi");
        addressNode.put("zipcode", "110020");

        phoneList.add("9923456469");
        phoneList.add("9886786786");

        employeeJsonNode.put("id", "123");
        employeeJsonNode.put("name", "Rahul Kumar");
        employeeJsonNode.set("address", addressNode);
        employeeJsonNode.set("phoneNumbers", phoneList);
        employeeJsonNode.put("role", "Developer");
        return employeeJsonNode;
    }

}
