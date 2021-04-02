package testServices;

import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import services.Files;
import parser.Page;

/**
 * Contains test cases for methods related to using buffered readers.
 */
public class FileTest {

    /**
     * Check correctness of method extracting the data from buffered reader.
     * @throws FileNotFoundException If path to file is invalid.
     */
    @Test
    public void testGetContent() throws FileNotFoundException {
        BufferedReader br = Files.getFromFile("src\\test\\java\\getContent.txt");
        String expected = "File containing\ntest  data\n";
        String actual = Files.getContent(br);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Check correctness of parsing method.
     * @throws FileNotFoundException If path to file is invalid.
     */
    @Test
    public void testParse() throws FileNotFoundException {
        BufferedReader br = Files.getFromFile("src\\test\\java\\parse.txt");
        Page page = new Page();
        String expected = "Heading Paragraph. Nested paragraph. ";
        String actual = page.parse(br).toString();
        Assert.assertEquals(expected, actual);
    }
}
