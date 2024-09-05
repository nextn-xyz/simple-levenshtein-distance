package xyz.nextn.jaccard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JaccardIndexTest {

    @ParameterizedTest
    @CsvSource({
            "'', '', 1.0",
            "'', 'abc', 0.0",
            "'abc', '', 0.0",
            "'abc', 'abc', 1.0",
            "'abc', 'def', 0.0",
            "'abc', 'def abc', 0.5",
            "'abc def', 'def abc', 1.0",
            "'leettteft', 'ritttght', 0.0",
            "'leettteft', 'ritttght leettteft', 0.5",
            "'leettteft', 'ritttght leettteft', 0.5",
            "'😁', '??', 0.0",
            "'😁', '😁', 1.0"
    })
    void shouldCorrectlyCalculateJaccardIndex(String str1, String str2, double expected) {
        assertEquals(expected, JaccardIndex.calculate(str1, str2));
    }

   @Test
    void testNullValues() {
        assertThrows(IllegalArgumentException.class, () -> JaccardIndex.calculate("null", null));
        assertThrows(IllegalArgumentException.class, () -> JaccardIndex.calculate(null, "null"));
        assertThrows(IllegalArgumentException.class, () -> JaccardIndex.calculate(null, null));
    }

}