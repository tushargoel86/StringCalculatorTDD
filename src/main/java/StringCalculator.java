import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String input, String separator) {
        if (input.length() == 0) return 0;
        String []inp = input.split(separator);
        checkForBalancedSeparator(input, separator, inp.length);
        return calculateSum(inp);
    }

    private void checkForBalancedSeparator(String input, String separator, int numberOfDigits) {
        Pattern pattern = Pattern.compile(separator);
        Matcher matcher = pattern.matcher(input);
        long count = matcher.results().count();
        if (count > (numberOfDigits - 1))
            throw new IllegalArgumentException("Separator count more than numbers");
    }

    private int calculateSum(String[] inp) {
        return Arrays.stream(inp).mapToInt(this::convertToInt).sum();
    }

    private int convertToInt(String value) {
        int item = Integer.parseInt(value);
        if (item < 0) throw new InvalidValueException("Negative value is not allowed.");
        return item;
    }
}
