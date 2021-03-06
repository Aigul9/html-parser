package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Page implements PageActions {
    private String pageUrl = "https://www.simbirsoft.com/";
    private int timeout = 10000;

    public Page() { }

    public Page(String url) {
        this.pageUrl = url;
    }

    public Page(int timeout) {
        this.timeout = timeout;
    }

    public Page(String url, int timeout) {
        this.pageUrl = url;
        this.timeout = timeout;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String url) {
        this.pageUrl = url;
    }

    @Override
    public Map<String, String> getPage() {
        Map<String, String> results = new HashMap<>();
        results.put("Content", "");
        results.put("Status", "Success");

        try {
            Document pageContent = Jsoup.connect(pageUrl).timeout(timeout).get();
            System.out.println(timeout);
            results.put("Content", pageContent.body().text());
        }
        catch(IOException | IllegalArgumentException e) {
            results.put("Status", e.toString());
        }

        return results;
    }

    public boolean isLetter(String name) {
        return name.matches("[a-zA-Zа-яА-Я]+");
    }

    @Override
    public TreeMap<String, Integer> countWords(String[] splitArray) {
        TreeMap<String, Integer> words = new TreeMap<>();

        for (String nextString : splitArray) {
            if (!isLetter(nextString)) {
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

    @Override
    public void display(Map<String, Integer> words) {
        for (Map.Entry<String, Integer> word: words.entrySet()) {
            System.out.printf("%s - %s%n", word.getKey(), word.getValue());
        }
    }
}
