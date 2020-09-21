import groovy.json.JsonSlurper
import org.hamcrest.Matcher

import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Stream

class Pixels {
    private final List<Pixel> pixels

    Pixels(Path filePath) {
        this.pixels = parse(filePath)
    }

    private static List<Pixel> parse(Path filePath) {
        List<HashMap<String, Object>> listOfPixelJsonObjects = ((new JsonSlurper().parse(filePath.toFile())) as List<HashMap<String, Object>>)
        List<Pixel> pixels = new ArrayList<Pixel>()
        for (HashMap<String, Object> pixelJson : listOfPixelJsonObjects) {
            try {
                pixels.add(new Pixel(
                        pixelJson["date"] as String,
                        Mood.parse(pixelJson["mood"] as String),
                        pixelJson["emotions"] as String,
                        pixelJson["notes"] as String))
            } catch (Exception e) {
                println "Failed to parse pixel from pixelJson ${pixelJson.toString()}"
                throw e
            }
        }
        return pixels
    }

    int count() {
        return pixels.size()
    }

    Pixel get(int index) {
        return pixels.get(index)
    }

    double meanMoodValue() {
        return streamedMeanMoodValue(pixels.stream())
    }

    double meanMoodValue(LocalDate startDate, LocalDate endDate) {
        return streamedMeanMoodValue(pixels.stream()
                .filter(Pixel.betweenDateFilter(startDate, endDate)))
    }

    double meanMoodValue(LocalDate startDate, LocalDate endDate, Matcher<String> notesMatcher) {
        return streamedMeanMoodValue(pixels.stream()
                .filter(Pixel.betweenDateFilter(startDate, endDate))
                .filter(Pixel.notesMatchFilter(notesMatcher)))
    }

    private double streamedMeanMoodValue(Stream<Pixel> pixelStream) {
        return pixelStream.mapToInt({ p -> p.mood().toValue() })
                .average()
                .getAsDouble()
    }
}
