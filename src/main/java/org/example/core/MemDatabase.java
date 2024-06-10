package org.example.core;

import java.util.HashMap;
import java.util.Map;

public class MemDatabase {

    private final Map<String, MemCollection> memData = new HashMap<>();

    public void listCollections() {
        System.out.println(" - - - ");
        memData.forEach((s, documents) -> {
            System.out.printf("--> %s%n", s);
        });
        System.out.println(" - - - ");
    }

    // It should be possible to create collections in a database.
    public MemCollection createCollection(String name) {

        sanitizeName(name);

        if (memData.containsKey(name)) {
            throw new RuntimeException("Collection with name" + name + "already exists");
        }

        MemCollection memCollection = new MemCollection();
        memData.put(name, memCollection);
        memCollection.updateId();

        return memCollection;
    }

    public MemCollection getCollection(String name) {
        return memData.get(name);
    }

    // It should be possible to delete collections in a database.
    public void deleteCollection(String name) {
        if (memData.remove(name) == null) {
            throw new RuntimeException("Collection with name" + name + "doesn't exists");
        }
    }

    private void sanitizeName(String name) {
        if (name == null) {
            throw new RuntimeException("Collection name cannot be empty");
        }
    }

}