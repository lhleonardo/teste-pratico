package br.com.wefin.testepratico.dtos;

import javax.validation.constraints.NotBlank;

public class PersonDTO {

    @NotBlank()
    private String name;

    @NotBlank()
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
