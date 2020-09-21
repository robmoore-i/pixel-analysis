import groovy.json.JsonSlurper

import java.nio.file.Path

class Pixels {
    private final List<Pixel> pixels

    Pixels(Path filePath) {
        List<HashMap<String, Object>> listOfPixelJsonObjects = ((new JsonSlurper().parse(filePath.toFile())) as List<HashMap<String, Object>>)
        List<Pixel> pixels = new ArrayList<Pixel>()
        for (HashMap<String, Object> pixelJson : listOfPixelJsonObjects) {
            pixels.add(new Pixel(
                    pixelJson["date"] as String,
                    Mood.parse(pixelJson["mood"] as String),
                    pixelJson["emotions"] as String,
                    pixelJson["notes"] as String))
        }
        this.pixels = pixels
    }

    int count() {
        return pixels.size()
    }

    Pixel get(int index) {
        return pixels.get(index)
    }
}
