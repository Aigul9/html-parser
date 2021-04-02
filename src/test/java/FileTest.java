import org.junit.Test;
import org.junit.Assert;

import services.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class FileTest {

    @Test
    public void testGetContent() throws FileNotFoundException {
        BufferedReader br = Files.getFromFile("src\\test\\java\\getContent.txt");
        String expected = "File containing\ntest  data\n";
        String actual = Files.getContent(br);
        Assert.assertEquals(expected, actual);
    }
}
