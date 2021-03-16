package env;

/** Represents a class of constants.
 */
public class Constants {
    /** Url of the HTML-page. */
    public static final String PAGE_URL = "https://www.google.com/";
    /** Idle time. */
    public static final int TIMEOUT = 10000;
    /** String of delimiters. */
    public static final String DELIMITERS = "\s\n\t\r,.!?;:\"\\[\\]()";
    /** Name of the database. */
    public static final String DB_NAME = "parsingDB";
    /** Username that allows to get an access to the database. */
    public static final String USERNAME = "root";
    /** Password that allows to get an access to the database. */
    public static final String PASSWORD = "root";
    /** Table name which stores list of unique words and their amount. */
    public static final String TABLE_NAME = "wordscount";
}
