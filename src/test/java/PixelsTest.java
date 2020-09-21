import org.junit.Test;

import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PixelsTest {
    @Test
    public void pixelParsing() {
        Pixels pixels = new Pixels(Paths.get("src/test/resources/example-pixels.json"));
        assertEquals(3, pixels.count());
        Pixel firstPixel = pixels.get(0);
        assertEquals(Mood.DECENT, firstPixel.mood());
        assertTrue("Pixel " + firstPixel.toString() + " was unexpectedly found to not contain the keyword 'KEYWORD'",
                firstPixel.containsKeyword("KEYWORD"));
        assertEquals(LocalDate.of(2019, 1, 1), firstPixel.date());
        assertEquals(LocalDate.of(2019, 10, 1), pixels.get(1).date());
    }

    @Test
    public void variousAnalytics() {
        Pixels pixels = new Pixels(Paths.get("src/test/resources/pixels-backup-21-09-2020.json"));
        assertEquals(630, pixels.count());
        assertEquals(4.4, pixels.meanMoodValue(), 0.02);
        assertEquals(4.3, pixels.meanMoodValue(
                LocalDate.of(2020, 2, 1),
                LocalDate.of(2020, 10, 1)), 0.02);
        assertEquals(4.3, pixels.meanMoodValue(
                LocalDate.of(2020, 2, 1),
                LocalDate.of(2020, 10, 1),
                NotesMatchers.mentions("gym")), 0.03);
    }
}
