package parser;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import services.Log;
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
            Document pageContent = Jsoup.connect(pageUrl).maxBodySize(0).timeout(timeout).get();
            Actions.disjoinElems(pageContent.select("*"));
            return pageContent.body().text();
        } catch(IllegalArgumentException | IOException e) {
            Log.logError(e.toString());
            return null;
        }
    }
}
