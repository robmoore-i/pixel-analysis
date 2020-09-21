import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class NotesMatchers {
    public static Matcher<String> mentions(String keyword) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof String)) {
                    return false;
                }
                String s = (String) item;
                return s.toLowerCase().contains(keyword.toLowerCase());
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
