package am.aca.twitter.text.analysis.entities.documents;

import java.util.ArrayList;
import java.util.List;

public class Documents {
    private List<Document> documents;

    public Documents() {
        this.documents = new ArrayList<Document>();
    }
    public void add(String id, String language, String text) {
        this.documents.add (new Document(id, language, text));
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void add(Document document) {
        this.documents.add(document);
    }
}
