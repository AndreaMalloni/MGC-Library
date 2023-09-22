package it.unicam.cs.MGC.model.queries;

import java.util.Map;

/**
 * A concrete implementation of both the RegexSparqlString and ParameterizableSparqlString interfaces
 * that represents a SPARQL query constructed from a provided string.
 *
 * <p>The StringQuery class extends the RegexSparqlString abstract class and implements the ParameterizableSparqlString
 * interface. It represents a SPARQL query constructed from a given string and provides methods to retrieve the raw SPARQL
 * query, extract variables and prefixes using regular expressions, and create parameterized SPARQL queries by replacing
 * specific variables with their corresponding values.</p>
 *
 * @see RegexSparqlString
 * @see ParameterizableSparqlString
 */
public class StringQuery extends RegexSparqlString implements ParameterizableSparqlString{
    private final String query;

    /**
     * Constructs a StringQuery object with the provided SPARQL query string.
     *
     * @param query The raw SPARQL query string to be represented by this object.
     */
    public StringQuery(String query) {
        this.query = query;
    }

    /**
     * Creates a parameterized SPARQL query by replacing a specific variable with its corresponding value.
     *
     * @param var   The variable to be replaced in the SPARQL string.
     * @param value The value that will replace the variable in the SPARQL string.
     * @return The parameterized SPARQL query string.
     * @throws IllegalArgumentException If the provided variable is not found in the SPARQL query.
     */
    @Override
    public String getString(String var, Object value) throws IllegalArgumentException{
        StringBuilder query = new StringBuilder(this.query);
        if (this.getVariables().contains(var)){
            query.insert(query.lastIndexOf("}") - 1, this.getFilter(var, value));
        } else {
            throw new IllegalArgumentException(String.format("Unknown query variable %s.", var));
        }
        return query.toString();
    }

    /**
     * Creates a parameterized SPARQL query by replacing multiple variables with their corresponding values
     * based on the given map of parameters.
     *
     * @param params A map containing variables as keys and their corresponding values to be replaced in the SPARQL string.
     * @return The parameterized SPARQL query string.
     * @throws IllegalArgumentException If any of the provided variables is not found in the SPARQL query.
     */
    @Override
    public String getString(Map<String, Object> params) throws IllegalArgumentException{
        StringBuilder query = new StringBuilder(this.query);
        for(String var : params.keySet()){
            Object value = params.get(var);

            if (this.getVariables().contains(var)){
                query.insert(query.lastIndexOf("}") - 1, this.getFilter(var, value));
            } else {
                throw new IllegalArgumentException(String.format("Unknown query variable %s.", var));
            }
        }
        return query.toString();
    }

    /**
     * Gets the raw SPARQL query string represented by this StringQuery object.
     *
     * @return The raw SPARQL query string.
     */
    @Override
    public String getString() {
        return this.query;
    }

    /**
     * Generates the SPARQL filter clause for the provided variable and value.
     *
     * @param var   The variable to be filtered.
     * @param value The value to be filtered with.
     * @return A string representing the SPARQL filter clause for the variable and value.
     */
    private String getFilter(String var, Object value){
        String filter = value instanceof String ? " FILTER ( %s = \"%s\" )" : " FILTER ( %s = %s )";
        return String.format(filter, var, value);
    }
}
