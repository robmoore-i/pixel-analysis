class Pixel {
    private final String date
    private final Mood mood
    private final String emotions
    private final String notes

    Pixel(String date, Mood mood, String emotions, String notes) {
        this.date = date
        this.mood = mood
        this.emotions = emotions
        this.notes = notes
    }

    Mood mood() {
        return mood
    }

    boolean containsKeyword(String keyword) {
        return notes.contains(keyword)
    }
}
