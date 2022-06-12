import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStringCalculator {

    public static final String COMMA_SEPARATOR = ",";
    public static final String NEW_LINE_SEPARATOR = "\n";

    @Test
    @DisplayName("Should return 0 for empty string")
    void testForEmptyString() {
        assertForCorrectSum("", 0, COMMA_SEPARATOR);
    }

    @Test
    @DisplayName("Should return same number when single number in input")
    void testForSingleNumber() {
        assertForCorrectSum("2", 2, COMMA_SEPARATOR);
    }

    @Test
    @DisplayName("Should return sum of 2 number when input contains 2 number")
    void testForSumWhenTwoNumbersPresent() {
        assertForCorrectSum("2,3", 5, COMMA_SEPARATOR);
    }

    @Test
    @DisplayName("Should throw exception when any of the number is negative")
    void testForNegativeNumber() {
        assertThrows(InvalidValueException.class,
                () -> new StringCalculator().add("1,-1", COMMA_SEPARATOR));
    }

    @Test
    @DisplayName("Should throw exception when input contains non integer values")
    void testForNonInteger() {
        assertThrows(NumberFormatException.class, () ->
                new StringCalculator().add("1,1, a, b", COMMA_SEPARATOR));
    }

    @Test
    @DisplayName("Should sum for multiple values")
    void testForMultipleInteger() {
        assertForCorrectSum("1,1,2,3", 7, COMMA_SEPARATOR);
    }

    @Test
    @DisplayName("New line should be treated as a new word separator")
    void testForNewLineAsSeparator() {
        assertForCorrectSum("1\n1\n2", 4, NEW_LINE_SEPARATOR);
    }

    @Test
    @DisplayName("Should be failed when multiple separators are available")
    void testForMultipleSeparators() {
        assertThrows(IllegalArgumentException.class, () ->
                new StringCalculator().add("1,1\n2", COMMA_SEPARATOR));
    }

    @Test
    @DisplayName("Should throw exception when separators are more than the numbers")
    void testForUnbalancedSeparator() {
        assertThrows(IllegalArgumentException.class, () ->
                new StringCalculator().add("1,1,", COMMA_SEPARATOR));
        assertThrows(IllegalArgumentException.class, () ->
                new StringCalculator().add(",1,1", COMMA_SEPARATOR));
        assertThrows(IllegalArgumentException.class, () ->
                new StringCalculator().add("1,,1", COMMA_SEPARATOR));
    }

    @Test
    @DisplayName("Should calculate the number of patterns matches in an input")
    void testForPattern() {
        Pattern pattern = Pattern.compile("[,]");
        Matcher matcher = pattern.matcher("1,2,");

        assertEquals(2, matcher.results().count());
    }

    void assertForCorrectSum(String input, int result, String separator) {
        StringCalculator stringCalculator = new StringCalculator();
        int output = stringCalculator.add(input, separator);
        assertEquals(result, output);
    }
}
