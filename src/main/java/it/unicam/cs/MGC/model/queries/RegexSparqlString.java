package it.unicam.cs.MGC.model.queries;

import it.unicam.cs.MGC.model.executors.RegexExecutor;
import it.unicam.cs.MGC.model.parsers.RegexMatchParser;

import java.util.Set;

/**
 * An abstract class implementing the SparqlString interface using regular expressions for variable and prefix extraction.
 *
 * <p>The RegexSparqlString class provides default implementations for the methods defined in the SparqlString interface
 * using regular expressions to extract variables and prefixes from the SPARQL query or update string.</p>
 *
 * @see SparqlString
 */
public abstract class RegexSparqlString implements SparqlString{

    /**
     * Gets the set of variables used in the SPARQL query or update using regular expression matching.
     *
     * @return A set of strings representing the variables used in the SPARQL string.
     */
    public Set<String> getVariables(){
        return new RegexMatchParser().parse(new RegexExecutor("\\?(\\w+)").execute(this.getString()));
    }

    /**
     * Gets the set of prefixes defined in the SPARQL query or update using regular expression matching.
     *
     * @return A set of strings representing the prefixes used in the SPARQL string.
     */
    public Set<String> getPrefixes(){
        return new RegexMatchParser().parse(
                new RegexExecutor("[A-Za-z0-9]+:.*<.*>").execute(this.getString()));
    }
}
