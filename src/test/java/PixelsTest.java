import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PixelsTest {
    @Test
    public void someTest() {
        Pixels pixels = new Pixels(Paths.get("src/test/resources/example-pixels.json"));
        assertEquals(2, pixels.count());
        Pixel firstPixel = pixels.get(0);
        assertEquals(Mood.DECENT, firstPixel.mood());
        assertTrue("Pixel " + firstPixel.toString() + " was unexpectedly found to not contain the keyword 'KEYWORD'",
                firstPixel.containsKeyword("KEYWORD"));
    }
}
