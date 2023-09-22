package it.unicam.cs.MGC.model.queries;

import java.util.Set;

/**
 * An interface representing a SPARQL query or update string.
 *
 * <p>The SparqlString interface provides methods for retrieving the raw SPARQL string,
 * extracting the variables used in the SPARQL query, and obtaining the prefixes defined in the string.</p>
 */
public interface SparqlString {

    /**
     * Gets the raw SPARQL query or update string.
     *
     * @return The raw SPARQL string.
     */
    public String getString();

    /**
     * Gets the set of variables used in the SPARQL query or update.
     *
     * @return A set of strings representing the variables used in the SPARQL string.
     */
    public Set<String> getVariables();

    /**
     * Gets the set of prefixes defined in the SPARQL query or update.
     *
     * @return A set of strings representing the prefixes used in the SPARQL string.
     */
    public Set<String> getPrefixes();

}
