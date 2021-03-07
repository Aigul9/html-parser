package parser;

import java.util.Map;

public class GetStats {
    public static void main(String[] args) {
        Page page = new Page();
        Delimiters delimiters = new Delimiters();

        try {
            String pageContent = page.getPage();
            Map<String, Integer> words = page.countWords(delimiters.splitPage(pageContent));
//            page.display();

            DB db = new DB();
//            db.writeToDB(words);
//            db.getFromDB();
            db.deleteFromDB();
            db.getFromDB();
            db.close();
        } catch(Exception e) {
            Log.logError(e);
        }



//        if (db_res.equals("OK")) {
//            System.out.println("OK");
//        }
//        else {
//            logger.logError(db_res);
//        }
    }
}
