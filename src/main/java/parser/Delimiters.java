package parser;

import static env.Constants.*;

/**
 * Represents delimiters and allows to split content based on
 * default or given string of delimiters.
 */
public class Delimiters {
    /** String of delimiters. */
    private String delimiters = format(DELIMITERS);

    /** Creates default delimiters. */
    public Delimiters() { }

    /**
     * Creates delimiters with given string.
     * @param delimiters String of delimiters.
     */
    public Delimiters(String delimiters) {
        this.delimiters = format(delimiters);
    }

    /**
     * Gets string of delimiters.
     * @return String of delimiters.
     */
    public String getDelimiters() {
        return delimiters;
    }

    /**
     * Changes delimiters.
     * @param delimiters String of delimiters.
     */
    public void setDelimiters(String delimiters) {
        this.delimiters = format(delimiters);
    }

    /**
     * Changes string of delimiters in order to use in regular expression.
     * @param delimiters String of delimiters.
     * @return String of formatted delimiters, so that any of matching symbols can be found.
     */
    private String format(String delimiters) {
        return String.format("%s%s%s", "[", delimiters, "]+");
    }

    /**
     * Splits given text using defined delimiters.
     * @param textFromDoc String that needs to be split.
     * @return Split text in array.
     */
    public String[] splitPage(String textFromDoc) {
        return textFromDoc.split(delimiters);
    }
}
