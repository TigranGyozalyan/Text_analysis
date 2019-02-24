package am.aca.twitter.text.analysis.dtos;

import am.aca.twitter.text.analysis.entities.documents.Document;

import java.util.List;

public class ResponseDto {


    private List<Document> documents;
    private List<String> errors;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
