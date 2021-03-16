import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.Assert;

import org.hamcrest.collection.IsMapContaining;
import parser.Actions;
import parser.Delimiters;
import parser.Page;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

/** Contains test cases.
 */
public class PageTest {

    /** Checks correctness of split method.
     */
    @Test
    public void testSplitPage() {
        String initialText = "Services include art direction, UX & UI design; design systems.";

        Delimiters delimiters = new Delimiters(",");
        String[] expected = {"Services include art direction", " UX & UI design; design systems."};
        Assert.assertArrayEquals(expected, delimiters.splitPage(initialText));

        delimiters.setDelimiters(",;.");
        expected = new String[]{"Services include art direction", " UX & UI design", " design systems"};
        Assert.assertArrayEquals(expected, delimiters.splitPage(initialText));

        delimiters.setDelimiters("\s");
        expected = new String[]{"Services", "include", "art", "direction,", "UX", "&", "UI", "design;",
                "design", "systems."};
        Assert.assertArrayEquals(expected, delimiters.splitPage(initialText));
    }

    /** Checks correctness of counting method.
     */
    @Test
    public void testCountWords() {
        String[] words = {"Мы", "мы", "МЫ", "мЫ", "предлагаем", "услуги", "по", "услуги"};
        Map<String, Integer> expected = Map.of(
                "мы", 4,
                "по", 1,
                "предлагаем", 1,
                "услуги", 2
        );
        Map<String, Integer> res = Actions.countWords(words);

        assertThat(res.size(), is(4));
        assertThat(res, IsMapContaining.hasEntry("мы", 4));
        assertThat(res, IsMapContaining.hasKey("по"));
        assertThat(res, IsMapContaining.hasValue(2));
        assertThat(res, is(expected));
    }
}
