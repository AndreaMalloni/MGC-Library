package it.unicam.cs.MGC;

import com.google.common.collect.Iterators;
import it.unicam.cs.MGC.model.executors.QueryExecutor;
import it.unicam.cs.MGC.model.executors.UpdateExecutor;
import it.unicam.cs.MGC.model.ontology.Builder;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutorTest {

    private final String testFilePath = "modelTest.owl";


    @Test
    public void testQueryExecution() {
        QueryExecutor executor = new QueryExecutor(new Builder().build(testFilePath));
        String testSelectionQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
        ResultSet results = executor.execute(testSelectionQuery);
        assertNotNull(results);
        assertEquals(5, Iterators.size(results));
    }

    @Test
    public void testUpdateExecution() {
        Model model = new Builder().build();
        UpdateExecutor executor = new UpdateExecutor(model);
        String testUpdateQuery =
                """
                PREFIX ex: <http://example.org/> 
                            
                INSERT DATA { ex:person ex:age '30' . }
                """;
        boolean success = executor.execute(testUpdateQuery);

        assertTrue(success);
        assertTrue(model.contains(
                model.getResource("http://example.org/person"),
                model.getProperty("http://example.org/age"),
                model.createLiteral("30")));


    }

    @Test
    public void testInvalidUpdateExecution() {
        Model model = new Builder().build(testFilePath);
        UpdateExecutor executor = new UpdateExecutor(model);

        String invalidSparqlQuery =
                """
                PREFIX ex: <http://example.org/> 
                            
                INSERT INTO { ex:person ex:age '30' . }
                """;
        boolean fail = executor.execute(invalidSparqlQuery);

        assertFalse(fail);
        assertFalse(model.contains(
                model.getResource("http://example.org/person"),
                model.getProperty("http://example.org/age"),
                model.createLiteral("30")));
    }
}