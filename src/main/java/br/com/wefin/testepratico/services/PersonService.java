package br.com.wefin.testepratico.services;

import java.util.List;
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

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(PersonDTO dto) {
        Document document = Document.from(dto.getDocumentNumber());

        if (this.checkPersonExists(document)) {
            throw new DuplicateDocumentException();
        }

        Person newPerson = new Person(dto.getName(), document);

        return this.personRepository.save(newPerson);
    }

    public Person update(UUID id, PersonDTO dto) {
        var existsPerson = this.personRepository.findById(id);

        if (existsPerson.isEmpty()) {
            throw new EntityNotFoundException("Não existe pessoa cadastrada com o id: " + id);
        }

        Document doc = Document.from(dto.getDocumentNumber());
        
        if (this.checkPersonExists(doc)) {
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
            throw new EntityNotFoundException("Não existe pessoa cadastrada com o id: " + id);
        }

        return existsPerson.get();
    }

    private boolean checkPersonExists(Document document) {
        List<Person> result = this.personRepository.findByDocument(document);

        return result.size() > 0;
    }

    public void delete(UUID id) {
        var existsPerson = this.personRepository.findById(id);

        if (existsPerson.isEmpty()) {
            throw new EntityNotFoundException("Não existe pessoa cadastrada com o id: " + id);
        }

        this.personRepository.delete(existsPerson.get());
    }

}
