package it.unicam.cs.MGC;

import it.unicam.cs.MGC.model.ontology.Builder;
import openllet.jena.PelletReasonerFactory;
import org.apache.jena.rdf.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {
    private final String testFilePath = "modelTest.owl";
    private final String testQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";

    @Test
    public void testBuildWithFilePath() {
        Builder builder = new Builder();
        Model model = builder.build(testFilePath);

        assertNotNull(model);
    }

    @Test
    public void testBuildWithReasoner() {
        Builder builder = new Builder();
        InfModel infModel = builder.build(PelletReasonerFactory.theInstance().create());

        assertNotNull(infModel);
    }

    @Test
    public void testBuildWithFilePathAndReasoner() {
        Builder builder = new Builder();
        InfModel infModel = builder.build(testFilePath, PelletReasonerFactory.theInstance().create());

        assertNotNull(infModel);
    }
}
