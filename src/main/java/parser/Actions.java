package parser;

import java.util.Map;

public interface Actions {
    default void display(Map<String, Integer> words) {
        for (Map.Entry<String, Integer> word: words.entrySet()) {
            System.out.printf("%s - %s%n", word.getKey(), word.getValue());
        }
    }
}
