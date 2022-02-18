package br.com.wefin.testepratico.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "people")
public class Person {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column()
    private String name;

    @Column()
    private Document document;

    public Person() {
    }

    public Document getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }
}
