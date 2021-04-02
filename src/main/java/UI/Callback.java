package UI;

/**
 * An interface which accepts the URL entered by user and invokes parsing processes.
 */
public interface Callback {
    /**
     * Callback function.
     * @param pageUrl URL of the page entered by user.
     */
    void callingBack(String pageUrl);
}
