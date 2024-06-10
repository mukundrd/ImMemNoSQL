package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Document {

    private final Map<String, Object> dataMap = new HashMap<>();

    private String id;

    public String getId() {
        return id;
    }

    public void updateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
            dataMap.put("id", id);
        }
    }

    // Ability to add a new key, value pair into the document.
    public void addNewProperty(String key, Object value) {

        if (dataMap.containsKey(key)) {
            throw new RuntimeException("Property with name " + key + " already exists");
        }

        dataMap.put(key, value);
    }

    // Ability to remove a key, value pair in the document.
    public boolean removeKey(String key) {
        return dataMap.remove(key) != null;
    }

    // Ability to update the value of a key in the document.
    public void updateProperty(String key, Object value) {

        if ("id".equals(key)) {
            throw new RuntimeException("Cannot update ID field");
        }

        if (!dataMap.containsKey(key)) {
            throw new RuntimeException("Property with name " + key + " doesn't exist");
        }

        dataMap.put(key, value);
    }

    @Override
    public String toString() {
        return dataMap.toString();
    }

    public Document createCopy() {
        Document copy = new Document();
        copy.dataMap.putAll(dataMap);
        copy.id = id;
        return copy;
    }

    public void updateAll(Document document) {
        dataMap.clear();
        document.removeKey("id");
        dataMap.putAll(document.dataMap);
        dataMap.put("id", id);
    }

    public Object getValueOf(String name) {
        return dataMap.get(name);
    }
}
