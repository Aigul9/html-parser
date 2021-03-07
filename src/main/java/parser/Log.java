package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static parser.Constants.*;

public  class Log {
    private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME);

    public static void logError(Exception exception) {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        logger.error(String.format("%s: %s", method, exception.toString()));
    }
}
