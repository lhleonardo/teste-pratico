package br.com.wefin.testepratico.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.wefin.testepratico.dtos.PersonDTO;
import br.com.wefin.testepratico.exceptions.InvalidDocumentException;
import br.com.wefin.testepratico.models.DocumentType;
import br.com.wefin.testepratico.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    private PersonService service;

    @BeforeEach
    public void before() {
        this.service = new PersonService(this.repository);
    }

    @BeforeEach
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }    
    @Test
    public void shouldReturnEmptyListWhenNoPeopleExists() {
        assertTrue(service.getAll().size() == 0);
    }

    @Test
    public void shoudNotCreatePersonWithInvalidDocument() {
        var dto = new PersonDTO("some-name", "some-document");
        assertThrows(InvalidDocumentException.class, () -> this.service.create(dto));
    }

    @Test
    public void shoudNotCreatePersonWithInvalidCPF() {
        var dto = new PersonDTO("some-name", "123.123.123-331");
        assertThrows(InvalidDocumentException.class, () -> this.service.create(dto));
    }

    @Test
    public void shoudNotCreatePersonWithInvalidCNPJ() {
        var dto = new PersonDTO("some-name", "12.123.123/1234-123");
        assertThrows(InvalidDocumentException.class, () -> this.service.create(dto));
    }

    @Test
    public void shouldBeAbleToCreateAPerson() {
        assertDoesNotThrow(() -> this.service.create(new PersonDTO("some-name", "111.111.111-11")));
        assertDoesNotThrow(() -> this.service.create(new PersonDTO("some-name", "11.111.111/1111-11")));
    }

    @Test
    public void shouldBeAbleToCreateAPersonWithCorrectDocumentType() {
        Mockito.when(this.repository.save(any())).thenAnswer(i -> i.getArgument(0));

        var cpfPerson = this.service.create(new PersonDTO("some-name", "111.111.111-11"));
        var cnpjPerson = this.service.create(new PersonDTO("some-name", "11.111.111/1111-11"));

        assertEquals(DocumentType.CPF, cpfPerson.getDocument().getType());
        assertEquals(DocumentType.CNPJ, cnpjPerson.getDocument().getType());
    }

}
