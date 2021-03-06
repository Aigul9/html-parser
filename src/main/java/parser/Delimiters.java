package parser;

public class Delimiters {
    private String delimiters;

    public Delimiters() {
        this.delimiters = "[\s\n\t\r,.!?;:\"\\[\\]()]+";
    }

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
