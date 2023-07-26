package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

	public static boolean nomeCognomeRegex(final String input) {
		// Compile regular expression
		final Pattern pattern = Pattern.compile("[A-Za-z]+", Pattern.CASE_INSENSITIVE);
		// Match regex against input

		final Matcher matcher = pattern.matcher(input);
		// Use results...
		return matcher.matches();

	}

	public static boolean UsernameRegex(final String input) {
		final Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$", Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(input);
		// Use results...
		return matcher.matches();
	}
}
