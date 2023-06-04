package maxxsoft.conversorCSV.aplication.util;

import java.util.Arrays;
import java.util.List;

public class DelimiterValidator {

    private static final List<String> VALID_DELIMITERS = Arrays.asList(",", ", ", "\t", ";", "|", ":");

    public static String validate(String delimiter) {
        if (!VALID_DELIMITERS.contains(delimiter)) {
            throw new RuntimeException("Delimitador não válido");
        } else {
            return delimiter;
        }
    }
}
