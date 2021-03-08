package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.commons.lang.StringUtils;
import services.Log;

import java.io.IOException;
import java.util.TreeMap;

import static env.Constants.*;

/** Represents a page with properties: pageUrl and timeout -
 * and allows to read its content and count number of words on the page.
 */
public class Page implements Actions {
    /** Url of the HTML-page. */
    private String pageUrl = PAGE_URL;
    /** Idle time. */
    private int timeout = TIMEOUT;

    /** Creates new page with default url and timeout. */
    public Page() { }

    /** Creates new page with given url.
     * @param url Url of the HTML-page.
     */
    public Page(String url) {
        this.pageUrl = url;
    }

    /** Creates new page with given timeout.
     * @param timeout Idle time.
     */
    public Page(int timeout) {
        this.timeout = timeout;
    }

    /** Creates new Page with given url and timeout.
     * @param url Url of the HTML-page.
     * @param timeout Idle time.
     */
    public Page(String url, int timeout) {
        this.pageUrl = url;
        this.timeout = timeout;
    }

    /** Gets url of the page.
     * @return Page's url in a string format.
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /** Changes url of the page.
     * @param url Url of the HTML-page.
     */
    public void setPageUrl(String url) {
        this.pageUrl = url;
    }

    /** Gets text content of the page.
     * @return Content of the page in a string format.
     * @exception IllegalArgumentException If pageUrl is not valid.
     */
    public String getPage() {
        try {
            Connection.Response resp = Jsoup.connect(pageUrl).method(Connection.Method.HEAD).execute();
            String length = resp.header("Content-Length");
            System.out.println(length);
            Document pageContent = Jsoup.connect(pageUrl).timeout(timeout).get();
            System.out.println(pageContent.outerHtml().length());
            return pageContent.body().text();
        } catch(IllegalArgumentException | IOException e) {
            Log.logError(e);
            return null;
        }
    }

    /** Counts amount of each word on the page.
     * @param splitArray Array of strings.
     * @return Map with properties: word and amount.
     */
    public static TreeMap<String, Integer> countWords(String[] splitArray) {
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
