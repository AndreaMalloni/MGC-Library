package it.unicam.cs.MGC.model.ontology;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.reasoner.Reasoner;

/**
 * An extension of the ModelBuilder interface that provides methods for building
 * RDF Models with reasoning capabilities using a Reasoner.
 *
 * <p>The InferenceModelBuilder interface extends the ModelBuilder interface
 * and adds methods for constructing RDF Models with reasoning capabilities
 * based on a specified Reasoner.</p>
 *
 * @see ModelBuilder
 */
public interface InferenceModelBuilder extends ModelBuilder{

    /**
     * Builds an RDF Model with reasoning capabilities using the provided Reasoner.
     *
     * @param reasoner The Reasoner used for inferring new triples based on the data in the Model.
     * @return The constructed RDF Model with reasoning capabilities.
     */
    public InfModel build(Reasoner reasoner);

    /**
     * Builds an RDF Model with reasoning capabilities by loading RDF data from a file specified by the given path,
     * and using the provided Reasoner for inference.
     * The path should point to a valid RDF file in formats such as RDF/XML, Turtle, N-Triples, etc.
     *
     * @param path     The path to the file containing RDF data.
     * @param reasoner The Reasoner used for inferring new triples based on the data in the Model.
     * @return The constructed RDF Model with reasoning capabilities and the data loaded from the file.
     */
    public InfModel build(String path, Reasoner reasoner);
}
