package org.example;

import org.example.core.MemCollection;
import org.example.core.MemDatabase;
import org.example.model.Document;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // testAddDeleteCollection();

        // testEditDocument();

        testCreateDocumentsAndSearch();
    }

    private static void testCreateDocumentsAndSearch() {
        MemDatabase database = buildMemDatabase();

        MemCollection notes = database.createCollection("notes");

        Document document = new Document();
        document.addNewProperty("name", "First Note");
        document.addNewProperty("prop", "1");
        notes.insertDocument(document);

        document = new Document();
        document.addNewProperty("name", 2);
        document.addNewProperty("prop", 1);
        notes.insertDocument(document);

        document = new Document();
        document.addNewProperty("name", "2");
        document.addNewProperty("prop", new int[]{1});
        notes.insertDocument(document);

        document = new Document();
        document.addNewProperty("name", 4);
        notes.insertDocument(document);

        System.out.println(notes);

        List<Document> documents = notes.findDocument("name", "2");
        System.out.println(documents);

        documents = notes.findDocument("name", 2);
        System.out.println(documents);

        documents = notes.findDocument("prop", 1);
        System.out.println(documents);
    }

    private static void testEditDocument() {
        MemDatabase database = buildMemDatabase();

        MemCollection notes = database.createCollection("notes");

        Document document = new Document();

        notes.insertDocument(document);

        document.addNewProperty("name", "First Note");

        System.out.println(notes);

        notes.updateDocument(document);

        System.out.println(notes);

        document.updateProperty("name", "First Edited Note");

        notes.updateDocument(document);

        System.out.println(notes);

        // Throw exception for below case
        document.updateProperty("id", "Can't update ID");
        notes.updateDocument(document);
    }

    @NotNull
    private static MemDatabase buildMemDatabase() {
        return new MemDatabase();
    }

    private static void testAddDeleteCollection() {
        MemDatabase database = buildMemDatabase();

        MemCollection notes = database.createCollection("notes");

        Document document = new Document();

        System.out.println(document.getId());

        notes.insertDocument(document);

        // add collection
        System.out.println(document.getId());

        // print collections in the database
        database.listCollections();

        // remove collection
        database.deleteCollection("notes");

        database.listCollections();
    }

}