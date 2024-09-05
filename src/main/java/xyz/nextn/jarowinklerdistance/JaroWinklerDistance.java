package xyz.nextn.jarowinklerdistance;
/**
 * Utility class for calculating the Jaro-Winkler distance between two strings.
 * <p>
 * This class cannot be instantiated.
 */
public class JaroWinklerDistance {

    /**
     * Private constructor to prevent instantiation.
     */
    private JaroWinklerDistance() {
        // Prevent instantiation
    }

    /**
     * Calculates the Jaro-Winkler distance between two strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The Jaro-Winkler distance between the two strings. The result is a double between 0 and 1, where 0 means no similarity and 1 means an exact match.
     * @throws IllegalArgumentException if either string is {@code null}.
     */
    public static double calculate(String s1, String s2) {
        final double defaultScalingFactor = 0.1;
        final double percentageRoundValue = 100.0;

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int[] mtp = matches(s1, s2);
        double m = mtp[0];
        if (m == 0) {
            return 0D;
        }
        double j = ((m / s1.length() + m / s2.length() + (m - mtp[1]) / m)) / 3;
        double jw = j < 0.7D ? j : j + Math.min(defaultScalingFactor, 1D / mtp[3]) * mtp[2] * (1D - j);
        return Math.round(jw * percentageRoundValue) / percentageRoundValue;
    }

    private static int[] matches(String s1, String s2) {
        String max, min;
        if (s1.length() > s2.length()) {
            max = s1;
            min = s2;
        } else {
            max = s2;
            min = s1;
        }

        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        boolean[] matchFlags = new boolean[max.length()];
        java.util.Arrays.fill(matchIndexes, -1);

        int matches = 0;
        for (int i = 0; i < min.length(); i++) {
            char c1 = min.charAt(i);
            for (int j = Math.max(i - range, 0), end = Math.min(i + range + 1, max.length()); j < end; j++) {
                if (!matchFlags[j] && c1 == max.charAt(j)) {
                    matchIndexes[i] = j;
                    matchFlags[j] = true;
                    matches++;
                    break;
                }
            }
        }
        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) {
                ms1[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) {
                ms2[si] = max.charAt(i);
                si++;
            }
        }

        int transpositions = 0;
        for (int i = 0; i < ms1.length; i++) {
            if (ms1[i] != ms2[i]) {
                transpositions++;
            }
        }

        int prefix = 0;
        for (int i = 0; i < min.length() && s1.charAt(i) == s2.charAt(i); i++) {
            prefix++;
        }

        return new int[]{matches, transpositions / 2, prefix, max.length()};
    }
}
