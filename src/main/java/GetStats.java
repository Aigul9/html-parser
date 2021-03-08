import parser.Delimiters;
import parser.Page;
import services.*;

import java.util.Map;

/** Class that initialises main components and contains calls of methods.
 */
public class GetStats {
    /**
     * Main method of the program.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Page page = new Page("https://www.simbirsoft.com/");
        Delimiters delimiters = new Delimiters();

        try {
            String pageContent = page.getPage();
            Map<String, Integer> words = Page.countWords(delimiters.splitPage(pageContent));
//            page.display(words);

            DB db = new DB();
//            db.writeToDB(words);
////            db.getFromDB();
            db.deleteFromDB();
//            db.writeToDB(words);
            db.getFromDB();
            db.close();
        } catch(Exception e) {
            Log.logError(e);
        }
    }
}
