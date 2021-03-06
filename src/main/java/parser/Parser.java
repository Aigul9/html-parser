package parser;

import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class Parser {

    static final Logger logger = LoggerFactory.getLogger("parser");
    static final String url = "https://www.simbirsoft.com/";

    public static void main(String[] args) {
        Document page;


        try {
            // class for reading data by url
            page = Jsoup.connect(url).get();
            String textFromDoc = page.body().text();

            // method for parsing the text
            String[] parsedText = textFromDoc.split("[\s\n\t\r,.!?;:\"\\[\\]()]+");

//            for (String i: parsedText) {
//                System.out.println(i);
//            }

            // class for counting
            TreeMap<String, Integer> wordsCount = new TreeMap<>();

            for (String nextString : parsedText) {
                if (!isLetter(nextString)) {
                    continue;
                }

                Integer count = wordsCount.get(nextString.toLowerCase());

                if (count == null) {
                    count = 1;
                }
                else {
                    count += 1;
                }

                wordsCount.put(nextString.toLowerCase(), count);
            }

            // printing method
            for (Map.Entry<String, Integer> word: wordsCount.entrySet()) {
                System.out.println(String.format("%s - %s", word.getKey(), word.getValue()));
            }

//            System.out.println(textFromDoc);
            logger.info("Success");
        }
        catch(IOException e) {
            logger.error(e.toString());
        }
    }

    public static boolean isLetter(String name) {
        return name.matches("[a-zA-Zа-яА-Я]+");
    }
}
