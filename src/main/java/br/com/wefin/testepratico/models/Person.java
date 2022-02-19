package br.com.wefin.testepratico.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class Person {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Embedded()
    private Document document;

    public Person() {
    }

    public Person(UUID id, String name, Document document) {
        this.id = id;
        this.name = name;
        this.document = document;
    }

    public Person(String name, Document document) {
        this.name = name;
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }
}
