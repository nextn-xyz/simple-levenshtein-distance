package xyz.nextn.phoneticsearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneticSearchTest {

    @Test
    void shouldReturnEmptyStringForNullInput() {
        assertEquals("", PhoneticSearch.encode(null));
    }

    @Test
    void shouldReturnEmptyStringForEmptyInput() {
        assertEquals("", PhoneticSearch.encode(""));
    }

    @Test
    void shouldReturnEmptyStringForNonAlphabeticInput() {
        assertEquals("", PhoneticSearch.encode("123"));
    }


    @ParameterizedTest
    @CsvSource({
            "'testing', 'T235'",
            "'this', 'T200'",
            "'The', 'T000'",
            "'quick', 'Q200'",
            "'brown', 'B650'",
            "'fox', 'F200'",
            "'jumps', 'J512'",
            "'over', 'O160'",
            "'the', 'T000'",
            "'lazy', 'L200'",
            "'dog', 'D200'"
    })
    void shouldReturnCorrectEncoding(String input, String expected) {
        assertEquals(expected, PhoneticSearch.encode(input));
    }

    @ParameterizedTest
    @CsvSource({
            "OBrien",
            "'OBrien",
            "O'Brien",
            "OB'rien",
            "OBr'ien",
            "OBri'en",
            "OBrie'n",
            "OBrien'"
    })
    void shouldReturnCorrectEncodingIgnoringApostrophes(String input) {
        assertEquals("O165", PhoneticSearch.encode(input));
    }


    @Test
    void shouldHaveTheSameSoundexCodeEnglish() {
        assertEquals("B600", PhoneticSearch.encode("beer"));
        assertEquals("B600", PhoneticSearch.encode("bear"));
        assertEquals("B600", PhoneticSearch.encode("bier"));
    }

    @Test
    void shouldHaveTheSameSoundexCodeFrench() {
        assertEquals("B600", PhoneticSearch.encode("bière"));
        assertEquals("B600", PhoneticSearch.encode("biare"));
    }

    @Test
    void shouldHaveTheSameSoundexCodeGerman() {
        assertEquals("B600", PhoneticSearch.encode("bier"));
        assertEquals("B600", PhoneticSearch.encode("bire"));
    }

}