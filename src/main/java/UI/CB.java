package UI;

import java.util.Map;

import parser.Actions;
import parser.Delimiters;
import parser.Page;
import services.DB;
import services.Log;
import services.Files;

/**
 * Implements callback interface.
 * */
public class CB implements Callback {

    /**
     * Contains calls of methods parsing the page and writing processed data to database.
     * @param pageUrl URL of the page.
     */
    @Override
    public void callingBack(String pageUrl) {

        String tableName = "wordscount";
        Delimiters delimiters = new Delimiters();

        try {
            Page page = new Page(pageUrl);
            Files.saveToFile("files\\page", ".html", Files.getFromUrl(pageUrl));
            StringBuffer data = page.parse(Files.getFromFile("files\\page.html"));
            Map<String, Integer> words = Actions.countWords(delimiters.splitPage(data.toString()));
            Actions.display(words);

            DB db = new DB();
            db.writeToDB(tableName, words);
        } catch(Exception e) {
            Log.logError(e.toString());
            System.out.println("Проверьте корректность URL.");
        }
    }
}
