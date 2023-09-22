package it.unicam.cs.MGC.model.queries;

import java.util.Map;

/**
 * An extension of the SparqlString interface that allows parameterization of SPARQL queries.
 *
 * <p>The ParameterizableSparqlString interface extends the SparqlString interface and provides methods
 * to create parameterized SPARQL queries by replacing specific variables with their corresponding values.</p>
 *
 * @see SparqlString
 */
public interface ParameterizableSparqlString extends SparqlString{

    /**
     * Creates a parameterized SPARQL query by replacing a specific variable with its corresponding value.
     *
     * @param var   The variable to be replaced in the SPARQL string.
     * @param value The value that will replace the variable in the SPARQL string.
     * @return The parameterized SPARQL query string.
     */
    public String getString(String var, Object value);

    /**
     * Creates a parameterized SPARQL query by replacing multiple variables with their corresponding values
     * based on the given map of parameters.
     *
     * @param params A map containing variables as keys and their corresponding values to be replaced in the SPARQL string.
     * @return The parameterized SPARQL query string.
     */
    public String getString(Map<String, Object> params);
}
