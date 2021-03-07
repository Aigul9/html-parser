package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.TreeMap;

import static parser.Constants.*;

public class Page implements Actions {
    private String pageUrl = PAGE_URL;
    private int timeout = TIMEOUT;

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

    public String getPage() {
        try {
            Document pageContent = Jsoup.connect(pageUrl).timeout(timeout).get();
            return pageContent.body().text();
        } catch(IOException | IllegalArgumentException e) {
            Log.logError(e);
            return null;
        }
    }

    public boolean isLetter(String name) {
        return name.matches("[a-zA-Zа-яА-Я]+");
    }

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
}
