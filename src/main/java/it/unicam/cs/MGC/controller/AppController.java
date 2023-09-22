package it.unicam.cs.MGC.controller;

import it.unicam.cs.MGC.model.executors.QueryExecutor;
import it.unicam.cs.MGC.model.parsers.SparqlSolutionParser;
import it.unicam.cs.MGC.model.queries.Queries;
import org.apache.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.util.Map.entry;

/**
 * This class represents an application state controller.
 */
public class AppController {

    private final Model model;
    private final QueryExecutor executor;

    public Model getModel() {
        return model;
    }

    public QueryExecutor getExecutor() {
        return executor;
    }

    public AppController(Model model, QueryExecutor executor) {
        this.model = model;
        this.executor = executor;
    }

    /**
     * retrieves all information regarding every book in the catalog from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getBooks() {
        return new SparqlSolutionParser().parse(this.executor.execute(Queries.GET_BOOKS_DATA.instance().getString()));
    }

    /**
     * retrieves all information regarding every book in the catalog with the given name
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getBooks(String name) {
        return new SparqlSolutionParser().parse(
                this.executor.execute(Queries.GET_BOOKS_DATA.instance().getString("?Nome", name)));
    }

    /**
     * retrieves all information regarding every book in the catalog located in the given section and shelf
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getBooks(int section, int shelf) {
        Map<String, Object> params = Map.ofEntries(
                entry("?Sezione", section),
                entry("?Scaffale", shelf));
        return new SparqlSolutionParser().parse(this.executor.execute(Queries.GET_BOOKS_DATA.instance().getString(params)));
    }

    /**
     * retrieves all information regarding every rent from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getRents() {
        return new SparqlSolutionParser().parse(this.executor.execute(Queries.GET_RENTINGS_DATA.instance().getString()));
    }

    /**
     * retrieves all information regarding every rent with the given status
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getRents(String status) {
        return new SparqlSolutionParser().parse(
                this.executor.execute(Queries.GET_RENTINGS_DATA.instance().getString("?Status", status)));
    }

    /**
     * retrieves all information regarding every rent with the given request date
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getRentsByDate(String requestDate) {
        return new SparqlSolutionParser().parse(
                this.executor.execute(Queries.GET_RENTINGS_DATA.instance().getString("?DataRichiesta", requestDate)));
    }

    /**
     * retrieves all information regarding every member from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getMembers() {
        return new SparqlSolutionParser().parse(this.executor.execute(Queries.GET_MEMBERS_DATA.instance().getString()));
    }

    /**
     * retrieves all information regarding every member with the given name
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getMembers(String name) {
        return new SparqlSolutionParser().parse(
                this.executor.execute(Queries.GET_MEMBERS_DATA.instance().getString("?Nome", name)));
    }

    /**
     * retrieves all information regarding every member with the given phone number
     * from the ontology source.
     *
     * @return the parsed results of the query (an iterator of HashMaps).
     */
    public Iterator<HashMap<String, Object>> getMembers(long phone) {
        return new SparqlSolutionParser().parse(
                this.executor.execute(Queries.GET_MEMBERS_DATA.instance().getString("?Cellulare", phone)));

    }
}
