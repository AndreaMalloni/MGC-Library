package it.unicam.cs.MGC.model.ontology;

import org.apache.jena.rdf.model.Model;

/**
 * An interface for building RDF Models.
 *
 * <p>Implementing classes should define how to construct the Model using the
 * provided build methods.</p>
 */
public interface ModelBuilder {

    /**
     * Builds an RDF Model based on a specific configuration or default settings.
     *
     * @return The constructed RDF Model.
     */
    public Model build();

    /**
     * Builds an RDF Model by loading RDF data from a file specified by the given path.
     * The path should point to a valid RDF file in formats such as RDF/XML, Turtle, N-Triples, etc.
     *
     * @param path The path to the file containing RDF data.
     * @return The constructed RDF Model with the data loaded from the file.
     */
    public Model build(String path);
}
