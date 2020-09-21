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

    int toValue() {
        if (this == GREAT) {
            return 5
        } else if (this == DECENT) {
            return 4
        } else {
            return 3
        }
    }
}