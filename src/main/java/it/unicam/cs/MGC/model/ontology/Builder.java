package it.unicam.cs.MGC.model.ontology;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;

/**
 * An implementation of the InferenceModelBuilder interface that provides methods for building RDF Models
 * with reasoning capabilities using a Reasoner.
 *
 * <p>The Builder class implements the InferenceModelBuilder interface and provides methods to construct
 * RDF Models with or without reasoning capabilities using a specified Reasoner.</p>
 *
 * @see InferenceModelBuilder
 */
public class Builder implements InferenceModelBuilder{

    /**
     * Builds an RDF Model with reasoning capabilities using the provided Reasoner.
     *
     * @param reasoner The Reasoner used for inferring new triples based on the data in the Model.
     * @return The constructed RDF Model with reasoning capabilities.
     */
    @Override
    public InfModel build(Reasoner reasoner) {
        Model model = ModelFactory.createDefaultModel();
        return ModelFactory.createInfModel(reasoner.bindSchema(model), model);
    }

    /**
     * Builds an RDF Model with reasoning capabilities by loading RDF data from a file specified by the given path,
     * and using the provided Reasoner for inference.
     * The path should point to a valid RDF file in formats such as RDF/XML, Turtle, N-Triples, etc.
     *
     * @param path     The path to the file containing RDF data.
     * @param reasoner The Reasoner used for inferring new triples based on the data in the Model.
     * @return The constructed RDF Model with reasoning capabilities and the data loaded from the file.
     */
    @Override
    public InfModel build(String path, Reasoner reasoner) {
        Model model = this.build(path);
        return ModelFactory.createInfModel(reasoner.bindSchema(model), model);
    }

    /**
     * Builds a default RDF Model without reasoning capabilities.
     *
     * @return The constructed default RDF Model.
     */
    @Override
    public Model build() {
        return ModelFactory.createDefaultModel();
    }

    /**
     * Builds an RDF Model by loading RDF data from a file specified by the given path.
     * The path should point to a valid RDF file in formats such as RDF/XML, Turtle, N-Triples, etc.
     *
     * @param path The path to the file containing RDF data.
     * @return The constructed RDF Model with the data loaded from the file.
     */
    @Override
    public Model build(String path) {
        return ModelFactory.createDefaultModel().read(path);
    }
}
