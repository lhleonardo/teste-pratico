package br.com.wefin.testepratico.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wefin.testepratico.exceptions.InvalidDocumentException;

@Embeddable()
public class Document {
    @Column(name = "document_number", nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType type;

    public Document(String number, DocumentType type) {
        this.number = number;
        this.type = type;
    }

    public Document() {}

    public String getNumber() {
        return number;
    }

    public DocumentType getType() {
        return type;
    }

    public static Document from(String plainContent) {

        String content = plainContent.replaceAll("[^0-9]+", "");

        // check if content contains only numbers
        if (!content.matches("^[0-9]{11,14}$")) {
            throw new InvalidDocumentException(content);
        }

        int length = content.trim().length();

        if (length != 11 && length != 14) {
            throw new InvalidDocumentException(content);
        }

        return new Document(content, length == 11 ? DocumentType.CPF : DocumentType.CNPJ);
    }

    @Override
    public boolean equals(Object obj) {
        return this.number.equals(((Document) obj).number);
    }

}
