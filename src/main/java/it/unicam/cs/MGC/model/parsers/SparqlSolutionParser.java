package it.unicam.cs.MGC.model.parsers;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * An implementation of the Parser interface that parses QuerySolution objects
 * from SPARQL query results and converts them to HashMaps.
 *
 * <p>The SparqlSolutionParser class implements the Parser interface to parse QuerySolution objects,
 * which represent individual solutions or bindings resulting from a SPARQL query execution.
 * It extracts the variable names and their corresponding values from the QuerySolution
 * and maps them into a HashMap with variable names as keys and their values as objects.</p>
 *
 * @see Parser
 */
public class SparqlSolutionParser implements Parser<HashMap<String, Object>, QuerySolution>{

    /**
     * Parses the provided QuerySolution object and maps the variable names and their values into a HashMap.
     *
     * @param data The QuerySolution object representing a single solution or binding from a SPARQL query result.
     * @return A HashMap containing variable names as keys and their corresponding values as objects.
     */
    @Override
    public HashMap<String, Object> parse(QuerySolution data) {
        Iterator<String> varNames = data.varNames();
        HashMap<String, Object> mappedSolution = new HashMap<>();

        while (varNames.hasNext()) {
            String name = varNames.next();
            mappedSolution.put(name, getValueFromRDFNode(data.get(name)));
        }
        return mappedSolution;
    }

    /**
     * Parses the provided ResultSet and converts the QuerySolution objects into a collection of HashMaps.
     *
     * @param data The ResultSet representing the result of a SPARQL query execution.
     * @return An Iterator over a collection of HashMaps, where each HashMap contains variable names as keys
     *         and their corresponding values as objects for each solution in the ResultSet.
     */
    public Iterator<HashMap<String, Object>> parse(ResultSet data) {
        ArrayList<HashMap<String, Object>> mapped = new ArrayList<>();
        while (data.hasNext()) {
            QuerySolution solution = data.next();
            mapped.add(this.parse(solution));
        }
        return mapped.iterator();
    }

    /**
     * Extracts a value or a resource from a given RDFNode.
     *
     * @param node the node from which the value is taken.
     * @return an object representing the value of the node. Is null if the value is not a literal nor a resource.
     */
    private Object getValueFromRDFNode(RDFNode node) {
        if (node.isLiteral()) {
            return node.asLiteral().getValue();
        } else if (node.isResource()) {
            return node.asResource();
        }

        return null;
    }
}
