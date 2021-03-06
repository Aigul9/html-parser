package parser;

import static parser.Constants.*;

public class Delimiters {
    private String delimiters = DELIMITERS;

    public Delimiters() { }

    public Delimiters(String delimiters) {
        this.delimiters = delimiters;
    }

    public String getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(String delimiters) {
        this.delimiters = delimiters;
    }

    public String[] splitPage(String textFromDoc) {
        return textFromDoc.split(delimiters);
    }
}
