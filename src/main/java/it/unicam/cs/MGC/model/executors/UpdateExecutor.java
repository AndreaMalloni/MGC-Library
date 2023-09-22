package it.unicam.cs.MGC.model.executors;

import org.apache.jena.query.QueryParseException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

/**
 * An implementation of the SparqlExecutor class that specializes in executing SPARQL
 * UPDATE queries on an RDF Model and returning a Boolean indicating the success of the update.
 *
 * <p>The UpdateExecutor class is designed to execute SPARQL UPDATE queries on an RDF Model
 * and return a Boolean value to indicate whether the update was successful or not.</p>
 */
public class UpdateExecutor extends SparqlExecutor<Boolean, String>{

    /**
     * Constructs a new UpdateExecutor with the specified RDF Model.
     *
     * @param model The RDF Model to be used for executing SPARQL UPDATE queries.
     */
    public UpdateExecutor(Model model) {
        super(model);
    }

    /**
     * Executes the given SPARQL UPDATE query on the associated RDF Model.
     *
     * @param query The SPARQL UPDATE query to be executed.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public Boolean execute(String query) {
        UpdateRequest updateRequest;
        try{
            updateRequest = UpdateFactory.create(query);
        } catch (QueryParseException e) {
            return false;
        }

        try {
            UpdateAction.execute(updateRequest, this.getModel());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
