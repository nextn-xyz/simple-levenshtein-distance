package xyz.nextn.jaccard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for calculating the Jaccard index between two strings.
 * <p>
 * This class cannot be instantiated.
 */
public class JaccardIndex {

    /**
     * Private constructor to prevent instantiation.
     */
    private JaccardIndex() {
        // Prevent instantiation
    }


    /**
     * Calculates the Jaccard index between two strings.
     *
     * @param str1 The first string.
     * @param str2 The second string.
     * @return The Jaccard index between the two strings.
     */
    public static double calculate(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }
        Set<String> set1 = tokenize(str1);
        Set<String> set2 = tokenize(str2);

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }

    private static Set<String> tokenize(String str) {
        String[] tokens = str.toLowerCase().split("\\s+");
        return new HashSet<>(Arrays.asList(tokens));
    }
}
