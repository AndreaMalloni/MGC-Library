package it.unicam.cs.MGC;

import com.google.common.collect.Iterators;
import it.unicam.cs.MGC.model.executors.QueryExecutor;
import it.unicam.cs.MGC.model.ontology.Builder;
import it.unicam.cs.MGC.model.parsers.SparqlSolutionParser;
import org.apache.jena.query.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final String testFilePath = "modelTest.owl";
    private final String testSelectionQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";


    @Test
    void testSolutionParse() {
        ResultSet results = new QueryExecutor(new Builder().build(testFilePath)).execute(testSelectionQuery);
        SparqlSolutionParser parser = new SparqlSolutionParser();

        assertNotNull(results);
        assertTrue(results.hasNext());
        HashMap<String, Object> parsedSolution = parser.parse(results.next());
        assertTrue(parsedSolution.containsKey("s"));
        assertTrue(parsedSolution.containsKey("p"));
        assertTrue(parsedSolution.containsKey("o"));
    }

    @Test
    void testResultsParse() {
        ResultSet results = new QueryExecutor(new Builder().build(testFilePath)).execute(testSelectionQuery);
        SparqlSolutionParser parser = new SparqlSolutionParser();

        assertNotNull(results);
        assertEquals(Iterators.size(results), 5);
    }
}