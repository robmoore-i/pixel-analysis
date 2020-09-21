enum Mood {
    BAD,
    DECENT,
    GREAT

    static Mood parse(String s) {
        int moodValue = Integer.parseInt(s)
        if (moodValue <= 3) {
            return BAD
        } else if (moodValue == 4) {
            return DECENT
        } else {
            return GREAT
        }
    }
}