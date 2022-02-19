package br.com.wefin.testepratico.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wefin.testepratico.models.Document;
import br.com.wefin.testepratico.models.Person;

public interface PersonRepository  extends JpaRepository<Person, UUID> {
    List<Person> findByDocument(Document document);
}
