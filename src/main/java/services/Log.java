package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Provides logging.
 */
public class Log {
    /** Logger with a name "parser". */
    private static final Logger logger = LoggerFactory.getLogger("parser");

    /** Tracks errors based on a configuration in logback.xml.
     * Gets method name that threw an exception and exception message and
     * passes this data to a logger.
     * @param exception Thrown exception.
     */
    public static void logError(Exception exception) {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        logger.error(String.format("%s: %s", method, exception.toString()));
    }
}
