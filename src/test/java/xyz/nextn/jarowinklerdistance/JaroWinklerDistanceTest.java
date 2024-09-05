package xyz.nextn.jarowinklerdistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class JaroWinklerDistanceTest {

    @ParameterizedTest
    @CsvSource({
            "'', '', 0.0",
            "'', 'a', 0.0",
            "'aaapppp', '', 0.0",
            "'frog', 'fog', 0.93",
            "'fly', 'ant', 0.0",
            "'elephant', 'hippo', 0.44",
            "'hippo', 'elephant', 0.44",
            "'hippo', 'zzzzzzzz', 0.0",
            "'hello', 'haloa', 0.76",
            "'hello', 'hello', 1.0",
            "'ABC Corporation', 'ABC Corp', 0.93",
            "'D N H Enterprises Inc', 'D & H Enterprises, Inc.', 0.95",
            "'My Gym Children''s Fitness Center', 'My Gym. Childrens Fitness', 0.92",
            "'PENNSYLVANIA', 'PENNCISYLVNIA', 0.88",
        })
    public void testCalculate(String s1, String s2, double expected) {
        double actual = JaroWinklerDistance.calculate(s1, s2);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    void testCalculateNull() {
        assertThrows(IllegalArgumentException.class, () -> JaroWinklerDistance.calculate("null", null));
        assertThrows(IllegalArgumentException.class, () -> JaroWinklerDistance.calculate(null, "null"));
        assertThrows(IllegalArgumentException.class, () -> JaroWinklerDistance.calculate(null, null));
    }

    @ParameterizedTest
    @CsvSource({
            "'©', '©', 1.0",
            "'©', '®', 0.0",
            "'©', '™', 0.0",
            "'©', '€', 0.0",
            "'©', '??❌', 0.0",
    })
    void testCalculateUnicode(String s1, String s2, double expected) {
        assertEquals(expected, JaroWinklerDistance.calculate(s1, s2));
    }

}