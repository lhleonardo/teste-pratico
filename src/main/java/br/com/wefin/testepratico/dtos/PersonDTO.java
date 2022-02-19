package br.com.wefin.testepratico.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotBlank()
    @NotNull()
    private String name;

    @NotNull()
    @Size(min = 11, max = 14)
    private String documentNumber;

    public PersonDTO() {

    }

    public PersonDTO(String name, String documentNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getName() {
        return name;
    }

}
