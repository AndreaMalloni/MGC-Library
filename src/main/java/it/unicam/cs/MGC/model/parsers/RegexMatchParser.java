package it.unicam.cs.MGC.model.parsers;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * An implementation of the Parser interface that parses a Matcher object
 * using a regular expression and returns a set of matched strings.
 *
 * <p>The RegexMatchParser class implements the Parser interface to parse a Matcher object,
 * which represents a successful match of a regular expression on some input data.
 * It extracts the matched strings from the Matcher and returns them as a set of strings.</p>
 *
 * @see Parser
 */
public class RegexMatchParser implements Parser<Set<String>, Matcher>{

    /**
     * Parses the provided Matcher object and extracts the matched strings as a set.
     *
     * @param data The Matcher object representing a successful match of a regular expression.
     * @return A set of strings containing the matched substrings.
     */
    @Override
    public Set<String> parse(Matcher data) {
        Set<String> variables = new HashSet<>();

        while (data.find()) {
            String variable = data.group();
            variables.add(variable);
        }
        return variables;
    }
}
