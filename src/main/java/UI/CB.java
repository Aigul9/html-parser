package UI;

import java.util.Map;

import parser.Actions;
import parser.Delimiters;
import parser.Page;
import services.Log;

/** Implements callback interface. */
public class CB implements Callback {

    /** URL of the page entered by user. */
    String pageUrl;

    /** Contains calls of methods parsing the page and writing processed data to database.
     * @param value URL of the page.
     */
    @Override
    public void callingBack(String value) {

        pageUrl = value;

        Page page = new Page(pageUrl);
        Delimiters delimiters = new Delimiters();

        try {
            String pageContent = page.getPage();
            Map<String, Integer> words = Actions.countWords(delimiters.splitPage(pageContent));
            Actions.display(words);

//            DB db = new DB();
//            db.writeToDB(words);
////            db.getFromDB();
//            db.deleteFromDB();
//            db.writeToDB(words);
//            db.getFromDB();
//            db.close();
        } catch(Exception e) {
            Log.logError(e.toString());
            System.out.println("Проверьте корректность URL.");
        }
    }
}
