package parser;

import java.io.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;

import services.Log;
import static env.Constants.*;

/**
 * Represents a page with properties: pageUrl and timeout -
 * and allows to read its content and count number of words on the page.
 */
public class Page extends HTMLEditorKit.ParserCallback implements Actions {
    /** Url of the HTML-page. */
    private String pageUrl = PAGE_URL;
    /** Indicator of pointer place. */
    private boolean inBody = false;
    /** Data from the page in String format. */
    private StringBuffer collectedData = new StringBuffer();

    /**
     * Creates new page with default url and timeout.
     */
    public Page() { }

    /**
     * Creates new page with given url.
     * @param url Url of the HTML-page.
     */
    public Page(String url) {
        this.pageUrl = url;
    }

    /**
     * Gets url of the page.
     * @return Page's url in a string format.
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Changes url of the page.
     * @param url Url of the HTML-page.
     */
    public void setPageUrl(String url) {
        this.pageUrl = url;
    }

    /**
     * Callback which handles the start of the required range.
     * @param t HTML-tag.
     * @param a Collections of tag attributes.
     * @param pos Current position.
     */
    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
    {
        if(t.equals(HTML.Tag.BODY)) {
            inBody = true;
        }
    }

    /**
     * Callback which handles the end of the required range.
     * @param t HTML-tag.
     * @param pos Current position.
     */
    public void handleEndTag(HTML.Tag t, int pos)
    {
        if(t.equals(HTML.Tag.BODY)) {
            inBody = false;
        }
    }

    /**
     * Callback which gets text content from the tag and saves it in StringBuffer.
     * @param data Text content inside a tag.
     * @param pos Current position.
     */
    public void handleText(char[] data, int pos)
    {
        if(inBody) {
            collectedData.append(data).append("\s");
        }
    }

    /**
     * Parses text from input stream.
     * @param br Reader that gets text from the input stream.
     * @return String of parsed text.
     */
    public StringBuffer parse(BufferedReader br) {
        ParserDelegator pd = new ParserDelegator();
        try {
            pd.parse(br, this, true);
        } catch (Exception e) {
            Log.logError(e.toString());
        }

        return collectedData;
    }
}
