package services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Allows to work with files.
 */
public class Files {
    /**
     * Writes text content to the file.
     * @param fileName Path to the file.
     * @param extension File extension.
     * @param content Data that needs to be saved.
     */
    public static void saveToFile(String fileName, String extension, String content) {
        System.out.println(String.format("%s%s%s", fileName, getCurrentDate(), extension));
        try(BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                        String.format("%s%s%s", fileName, getCurrentDate(), extension)))) {
            bw.write(content);
        } catch(IOException e) {
            Log.logError("Incorrect path.");
        }
    }

    /**
     * Gets current date in specified format.
     * @return Date in custom format.
     */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("_dd-MM-yyyy_HH-mm-ss");
        return formatter.format(date);
    }

    /**
     * Creates buffering input stream based on file.
     * @param fileName Path to the file.
     * @return Buffered input stream.
     * @throws FileNotFoundException If file with given name does not exist in the user's file system.
     */
    public static BufferedReader getFromFile(String fileName) throws FileNotFoundException {
        return new BufferedReader(new FileReader(fileName));
    }

    /**
     * Gets HTML-content by provided url.
     * @param pageUrl Url of the page.
     * @return HTML-content of the page.
     * @throws IOException If the connection is not established.
     */
    public static String getFromUrl(String pageUrl) throws IOException {
        URL url = new URL(pageUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        return getContent(new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream())));
    }

    /**
     * Reads text content from the input stream.
     * @param br Reader that gets text from the input stream.
     * @return Data from the file in string format.
     */
    public static String getContent(BufferedReader br) {
        StringBuilder data = new StringBuilder();
        try(br) {
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch(IOException e) {
            Log.logError(e.toString());
        }

        return data.toString();
    }
}
