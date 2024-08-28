package xyz.nextn.levenshteindistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
class LevenshteinDistanceTest {


    @ParameterizedTest
    @CsvSource({
            "'kitten', 'kitten', 0",
            "'kitten', 'sitting', 3",
            "'flaw', 'lawn', 2",
            "'gumbo', 'gambol', 2",
            "'book', 'back', 2",
            "'', 'abc', 3",
            "'abc', '', 3",
            "'', '', 0",
            "'abc', 'abc', 0",
            "'----', '__', 4",
    })
    void testCalculate(String s1, String s2, int expected) {
        assertEquals(expected, LevenshteinDistance.calculate(s1, s2));
    }
    @Test
    void testCalculateNull() {
        assertThrows(NullPointerException.class, () -> LevenshteinDistance.calculate("null", null));
        assertThrows(NullPointerException.class, () -> LevenshteinDistance.calculate(null, "null"));
        assertThrows(NullPointerException.class, () -> LevenshteinDistance.calculate(null, null));
    }
    @ParameterizedTest
    @CsvSource({
            "'🐱', '🐱', 0",
            "'🐱', '🐶', 1",
            "'🐱', '🐭', 1",
            "'🐱', '🐰', 1",
            "'🐱', '😬❌', 2",
            "'╰(*°▽°*)╯','(┬┬﹏┬┬)', '7'",
    })
    void testCalculateEmoji(String s1, String s2, int expected) {
        assertEquals(expected, LevenshteinDistance.calculate(s1, s2));
    }

    @ParameterizedTest
    @CsvSource({
            "'©', '©', 0",
            "'©', '®', 1",
            "'©', '™', 1",
            "'©', '€', 1",
    })
    void testCalculateUnicode(String s1, String s2, int expected) {
        assertEquals(expected, LevenshteinDistance.calculate(s1, s2));
    }


}