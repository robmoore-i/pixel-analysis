import org.hamcrest.Matcher

import java.time.LocalDate
import java.util.function.Predicate

class Pixel {
    private final LocalDate date
    private final Mood mood
    private final String emotions
    private final String notes

    Pixel(String date, Mood mood, String emotions, String notes) {
        this.date = parseDate(date)
        this.mood = mood
        this.emotions = emotions
        this.notes = notes
    }

    static Predicate<Pixel> betweenDateFilter(LocalDate startDate, LocalDate endDate) {
        return {
            pixel -> pixel.date.isAfter(startDate.minusDays(1)) && pixel.date.isBefore(endDate.plusDays(1))
        }
    }

    static Predicate<Pixel> notesMatchFilter(Matcher<String> notesMatcher) {
        return {
            pixel -> notesMatcher.matches(pixel.notes)
        }
    }

    LocalDate date() {
        return date
    }

    Mood mood() {
        return mood
    }

    boolean containsKeyword(String keyword) {
        return notes.contains(keyword)
    }

    private static LocalDate parseDate(String s) {
        String year = s.substring(0, 4)
        String month = s.substring(5, 7)
        String day = s.substring(8, 10)
        return LocalDate.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day))
    }
}
