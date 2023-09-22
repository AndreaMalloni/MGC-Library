package it.unicam.cs.MGC.model.executors;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;

/**
 * An implementation of the SparqlExecutor class that specializes in executing SPARQL
 * SELECT queries on an RDF Model and returning the result as a ResultSet.
 *
 * <p>The QueryExecutor class is designed to execute SPARQL SELECT queries on an RDF Model
 * and return the results as a ResultSet, which represents the solution bindings of the query.</p>
 *
 * @see ResultSet
 */
public class QueryExecutor extends SparqlExecutor<ResultSet, String>{

    /**
     * Constructs a new QueryExecutor with the specified RDF Model.
     *
     * @param model The RDF Model to be used for executing SPARQL SELECT queries.
     */
    public QueryExecutor(Model model) {
        super(model);
    }

    /**
     * Executes the given SPARQL SELECT query on the associated RDF Model and returns the result as a ResultSet.
     *
     * @param query The SPARQL SELECT query to be executed.
     * @return The ResultSet representing the solution bindings of the query.
     * @throws IllegalArgumentException If the provided query is not a SPARQL SELECT query.
     */
    @Override
    public ResultSet execute(String query) throws IllegalArgumentException {
        Query runnableQuery = QueryFactory.create(query);
        if (!runnableQuery.isSelectType()) {
            throw new IllegalArgumentException("This executor only accepts selection queries.");
        }

        try (QueryExecution execution = QueryExecutionFactory.create(runnableQuery, this.getModel())) {
            return ResultSetFactory.copyResults(execution.execSelect());
        }
    }
}
