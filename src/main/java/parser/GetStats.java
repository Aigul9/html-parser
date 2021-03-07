package parser;

import java.util.Map;

public class GetStats {
    public static void main(String[] args) {
        Page page = new Page("https://georgebadea.com/");
        Delimiters delimiters = new Delimiters();

        try {
            String pageContent = page.getPage();
            Map<String, Integer> words = Page.countWords(delimiters.splitPage(pageContent));
            page.display(words);

//            DB db = new DB();
//            db.writeToDB(words);
////            db.getFromDB();
//            db.deleteFromDB();
//            db.getFromDB();
//            db.close();
        } catch(Exception e) {
            Log.logError(e);
        }
    }
}
