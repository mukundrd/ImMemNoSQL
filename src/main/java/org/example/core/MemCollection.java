package org.example.core;

import org.example.model.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//TODO: make set a composition, not is a
public class MemCollection {

    private final Set<Document> data = new HashSet<>();

    private String id;

    public String getCollectionId() {
        return id;
    }

    public void updateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    // The user should be able to insert a document of JSON type into a collection.
    public void insertDocument(Document document) {
        sanitizeCollection();

        if (document == null) {
            throw new RuntimeException("Null Document");
        }
        document.updateId();

        Document copy = document.createCopy();
        data.add(copy);
    }

    public Document findDocument(String docId) {
        sanitizeCollection();
        List<Document> documents = data.stream().filter(d -> d.getId().equals(docId)).toList();
        return documents.get(0);
    }

    private void sanitizeCollection() {
        if (id == null) {
            throw new RuntimeException("Collection is not part of database");
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public void updateDocument(Document document) {
        findDocument(document.getId()).updateAll(document);
    }

    public List<Document> findDocument(String name, Object value) {
        return data.stream().filter(d -> matchValue(name, value, d)).toList();
    }

    private boolean matchValue(String name, Object value, Document d) {
        Object val = d.getValueOf(name);
        if (val == null) return false;
        /*if (val.getClass().isArray()) {
            Object[] objects = (Object[]) val;
            if (objects.length == 1) {
                return objects[0].equals(value);
            }
            return false;
        }*/
        return val.equals(value);
    }

    private <T> T getValFromArray(T[] arr) {
        return arr[0];
    }
}
