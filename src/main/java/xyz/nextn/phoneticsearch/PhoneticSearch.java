package xyz.nextn.phoneticsearch;

/**
 * Phonetic search using Soundex algorithm.
 */
public class PhoneticSearch {

    private PhoneticSearch() {
        // Private constructor to hide the implicit public one
    }

    /**
     * Soundex encoding of the input string.
     *
     * @param input the string to encode
     * @return the Soundex code
     */
    public static String encode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Convert the input to uppercase and remove non-alphabetic characters
        input = input.toUpperCase().replaceAll("[^A-Z]", "");

        if (input.isEmpty()) {
            return "";
        }

        // The first letter is retained
        char[] soundex = new char[4];
        soundex[0] = input.charAt(0);

        // The following letters are encoded according to the Soundex rules
        int count = 1;
        char lastCode = getMappingCode(soundex[0]);

        for (int i = 1; i < input.length() && count < 4; i++) {
            char currentChar = input.charAt(i);
            char currentCode = getMappingCode(currentChar);

            if (currentCode != '0' && currentCode != lastCode) {
                soundex[count++] = currentCode;
            }
            lastCode = currentCode;
        }

        // Fill with '0' if the code is shorter than 4 characters
        while (count < 4) {
            soundex[count++] = '0';
        }

        return new String(soundex);
    }

    /**
     * Mapping from letters to Soundex codes.
     *
     * @param c the letter to encode
     * @return the Soundex code
     */
    private static char getMappingCode(char c) {
        return switch (c) {
            case 'B', 'F', 'P', 'V' -> '1';
            case 'C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z' -> '2';
            case 'D', 'T' -> '3';
            case 'L' -> '4';
            case 'M', 'N' -> '5';
            case 'R' -> '6';
            default -> '0'; // 'A', 'E', 'I', 'O', 'U', 'H', 'W', 'Y'
        };
    }

}
