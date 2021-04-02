package parser;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Contains common actions for different classes.
 */
public interface Actions {
    /**
     * Writes data from a map in a console.
     * @param words Map with two fields: String key and Integer value.
     */
    static void display(Map<String, Integer> words) {
        for (Map.Entry<String, Integer> word: words.entrySet()) {
            System.out.printf("%s - %s%n", word.getKey(), word.getValue());
        }
    }

    /**
     * Counts amount of each word on the page.
     * @param splitArray Array of strings.
     * @return Map with properties: word and amount.
     */
    static TreeMap<String, Integer> countWords(String[] splitArray) {
        TreeMap<String, Integer> words = new TreeMap<>();

        for (String nextString : splitArray) {
            if (!StringUtils.isAlpha(nextString)) {
                continue;
            }

            Integer count = words.get(nextString.toLowerCase());

            if (count == null) {
                count = 1;
            } else count += 1;

            words.put(nextString.toLowerCase(), count);
        }

        return words;
    }
}
