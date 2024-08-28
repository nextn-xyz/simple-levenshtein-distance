package xyz.nextn.levenshteindistance;

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
    })
    void testCalculate(String s1, String s2, int expected) {
        assertEquals(expected, LevenshteinDistance.calculate(s1, s2));
    }
}