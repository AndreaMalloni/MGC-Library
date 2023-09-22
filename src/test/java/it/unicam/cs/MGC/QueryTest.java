package it.unicam.cs.MGC;

import it.unicam.cs.MGC.model.queries.Queries;
import it.unicam.cs.MGC.model.queries.StringQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

class QueryTest {

    @Test
    void testGetVariables() {
        Set<String> selectionQueryExpectedVars = new HashSet<>(
                Arrays.asList("?s", "?p", "?o"));
        String testQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
        Set<String> selectionVariables = new StringQuery(testQuery).getVariables();

        assertEquals(3, selectionVariables.size());
        assertEquals(selectionQueryExpectedVars, selectionVariables);
    }

    @Test
    void testGetPrefixes() {
        Set<String> selectionQueryExpectedPrefixes = new HashSet<>(
                Arrays.asList(
                        "rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>",
                        "bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>"));
        String testQuery =
                """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
                
                SELECT ?s ?p ?o WHERE { ?s ?p ?o }
                """;
        Set<String> prefixes = new StringQuery(testQuery).getPrefixes();

        assertEquals(2, prefixes.size());
        assertEquals(selectionQueryExpectedPrefixes, prefixes);
    }

    @Test
    void testParameterizedGetString() {
        String expectedQuery = "SELECT ?s ?p ?o WHERE { ?s ?p ?o FILTER ( ?o = 343 ) FILTER ( ?s = \"test\" ) }";
        Map<String, Object> params = Map.ofEntries(
                entry("?s", "test"),
                entry("?o", 343));
        String query = new StringQuery("SELECT ?s ?p ?o WHERE { ?s ?p ?o }").getString(params);
        assertEquals(query.length(), expectedQuery.length());
    }
}