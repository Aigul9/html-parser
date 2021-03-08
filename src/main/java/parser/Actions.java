package parser;

import java.util.Map;

/** Contains common actions for different classes.
 */
public interface Actions {
    /** Writes data from a map in a console.
     * @param words Map with two fields: String key and Integer value.
     */
    default void display(Map<String, Integer> words) {
        for (Map.Entry<String, Integer> word: words.entrySet()) {
            System.out.printf("%s - %s%n", word.getKey(), word.getValue());
        }
    }
}
