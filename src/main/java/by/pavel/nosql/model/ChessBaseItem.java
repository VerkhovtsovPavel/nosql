package by.pavel.nosql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("base")
public class ChessBaseItem {
    @Id
    private String id;

    private String base;

    public ChessBaseItem() {}

    public ChessBaseItem(String base) {
        this.base = base;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}