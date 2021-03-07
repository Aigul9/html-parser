package parser;

public class Constants {
    public static final String PAGE_URL = "https://www.simbirsoft.com/";
    public static final int TIMEOUT = 10000;

    public static final String DELIMITERS = "[\s\n\t\r,.!?;:\"\\[\\]()]+";

    public static final String LOGGER_NAME = "parser";

    public static final String DB = "jdbc:mysql://localhost:3306/parsingDB?useUnicode=true&characterEncoding=utf-8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String TABLE_NAME = "wordscount";
}
