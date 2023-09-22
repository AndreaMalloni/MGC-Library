package it.unicam.cs.MGC.model.executors;

import org.apache.jena.rdf.model.Model;

/**
 * An abstract implementation of the Executor interface that provides the base functionality
 * for executing SPARQL (SPARQL Protocol and RDF Query Language) operations on an RDF Model.
 *
 * <p>The SparqlExecutor class is designed to be extended by specific SPARQL operation classes
 * that need to execute queries or other operations on an RDF Model.</p>
 *
 * @param <K> The type of the result returned by the execute method.
 * @param <V> The type of the input parameter for the execute method.
 */
public abstract class SparqlExecutor<K, V> implements Executor<K, V> {

    private Model model;

    /**
     * Constructs a new SparqlExecutor with the specified RDF Model.
     *
     * @param model The RDF Model to be used for SPARQL operations.
     */
    protected SparqlExecutor(Model model) {
        this.model = model;
    }

    /**
     * Gets the RDF Model associated with this SparqlExecutor.
     *
     * @return The RDF Model used for SPARQL operations.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets the RDF Model to be used for SPARQL operations.
     *
     * @param model The new RDF Model to be set.
     */
    public void setModel(Model model) {
        this.model = model;
    }
}
