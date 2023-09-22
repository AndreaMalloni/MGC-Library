package it.unicam.cs.MGC.model.executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the Executor interface that uses a regular expression (regex) pattern
 * to implement some of the methods.
 */
public class RegexExecutor implements Executor<Matcher, String>{

    private final Pattern regex;

    /**
     * Constructs a new RegexExecutor with the specified regular expression pattern.
     *
     * @param regex The regular expression pattern to be used for matching.
     */
    public RegexExecutor(String regex) {
        this.regex = Pattern.compile(regex);;
    }

    /**
     * Executes a match operation on the given input data using the previously defined
     * regular expression pattern and returns the corresponding Matcher object.
     *
     * @param data The input data to be matched against the regular expression pattern.
     * @return A Matcher object containing information about the match operation.
     */
    @Override
    public Matcher execute(String data) {
        return this.regex.matcher(data);
    }

    /**
     * Gets the regular expression pattern used by this RegexExecutor.
     *
     * @return The regular expression pattern.
     */
    public Pattern getRegex() {
        return regex;
    }
}
