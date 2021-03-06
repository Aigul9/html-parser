package parser;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

interface PageActions {
    Map<String, String> getPage();
    TreeMap<String, Integer> countWords(String[] splitArray);
    void display(Map<String, Integer> words);
}
