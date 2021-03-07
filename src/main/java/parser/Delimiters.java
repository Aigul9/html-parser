package parser;

import static env.Constants.*;

/** Class that contains string of delimiters.
 */
public class Delimiters {
    private String delimiters = format(DELIMITERS);

    public Delimiters() { }

    public Delimiters(String delimiters) {
        this.delimiters = format(delimiters);
    }

    public String getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(String delimiters) {
        this.delimiters = format(delimiters);
    }

    private String format(String delimiters) {
        return String.format("%s%s%s", "[", delimiters, "]+");
    }

    public String[] splitPage(String textFromDoc) {
        return textFromDoc.split(delimiters);
    }
}
