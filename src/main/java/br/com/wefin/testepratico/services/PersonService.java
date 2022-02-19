package br.com.wefin.testepratico.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.wefin.testepratico.dtos.PersonDTO;
import br.com.wefin.testepratico.exceptions.DuplicateDocumentException;
import br.com.wefin.testepratico.models.Document;
import br.com.wefin.testepratico.models.Person;
import br.com.wefin.testepratico.repositories.PersonRepository;

@Service
public class PersonService {

    private static final String ENTITY_NOT_FOUND_MESSAGE = "Não existe pessoa cadastrada com o id: %s";

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(PersonDTO dto) {
        Document document = Document.from(dto.getDocumentNumber());

        if (this.documentAlreadyExists(document).isPresent()) {
            throw new DuplicateDocumentException();
        }

        Person newPerson = new Person(dto.getName(), document);

        return this.personRepository.save(newPerson);
    }

    public Person update(UUID id, PersonDTO dto) {
        var existsPerson = this.personRepository.findById(id);

        if (existsPerson.isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        }

        Document doc = Document.from(dto.getDocumentNumber());

        if (this.documentAlreadyExists(doc).isPresent()) {
            throw new DuplicateDocumentException();
        }

        var personToUpdate = new Person(id, dto.getName(), doc);

        return this.personRepository.save(personToUpdate);
    }

    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    public Person findById(UUID id) {
        var existsPerson = this.personRepository.findById(id);

        if (existsPerson.isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        }

        return existsPerson.get();
    }

    public Optional<Person> documentAlreadyExists(Document document) {
        return this.personRepository.findByDocument(document).stream().findAny();
    }

    public void delete(UUID id) {
        var existsPerson = this.personRepository.findById(id);

        if (existsPerson.isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        }

        this.personRepository.delete(existsPerson.get());
    }

}
