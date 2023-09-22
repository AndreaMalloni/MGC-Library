package it.unicam.cs.MGC.model.queries;

/**
 * An enumeration of predefined SPARQL queries represented as StringQuery instances.
 *
 * <p>The Queries enum provides a set of predefined SPARQL queries for different operations,
 * each represented as a StringQuery instance. It allows easy access to these queries for
 * executing them against a SPARQL endpoint or RDF dataset.</p>
 *
 * @see StringQuery
 */
public enum Queries {
    GET_BOOKS_DATA(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            SELECT ?ISBN ?Nome ?Autore ?Sezione ?Scaffale ?Status
            WHERE {
                ?book rdf:type bib:Libro.
                ?book bib:ISBN ?ISBN.
                ?book bib:Nome ?Nome.
                ?shelf rdf:type bib:Scaffale.
                ?book bib:ContenutoIn ?shelf.
                ?shelf bib:Identificativo ?Scaffale.
                ?author rdf:type bib:Autore.
                ?book bib:ScrittoDa ?author.
                ?author bib:Nome ?Autore.
                ?section rdf:type bib:Sezione.
                ?shelf bib:ParteDi ?section.
                ?section bib:Identificativo ?Sezione.
                bind( if(exists{?book bib:OggettoNoleggio ?rent.
                ?rent rdf:type bib:NoleggioAccettato.
                FILTER NOT EXISTS {?rent rdf:type bib:NoleggioTerminato.}},
                "Noleggiato",
                 "Disponibile") as ?Status)
            }
            """)),

    GET_MEMBERS_DATA(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            SELECT ?ID ?Nome ?Cognome ?Cellulare ?Indirizzo ?Email (COUNT(?rent) as ?NoleggiAttivi)
            WHERE {
                ?member rdf:type bib:Abbonato.
                ?member foaf:name ?Nome.
                ?member foaf:surname ?Cognome.
                OPTIONAL {?member bib:Cellulare ?Cellulare.}
                ?member bib:Indirizzo ?Indirizzo.
                OPTIONAL {?member bib:Email ?Email.}
                ?card rdf:type bib:Tessera.
                ?card bib:PossedutaDa ?member.
                ?card bib:Identificativo ?ID.
                ?rent rdf:type bib:NoleggioAccettato.
                ?member bib:Richiede ?rent.
                FILTER NOT EXISTS {?rent rdf:type bib:NoleggioTerminato.}
            }
            GROUP BY ?ID ?Nome ?Cognome ?Cellulare ?Indirizzo ?Email
            """)),

    GET_RENTINGS_DATA(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            SELECT ?DataRichiesta ?PrevistaScadenza ?Restituzione ?Status ?Libro
            WHERE {
                ?rent rdf:type bib:Noleggio.
                ?rent bib:DataAvvenimento ?DataRichiesta.
                OPTIONAL {?rent bib:DataScadenza ?PrevistaScadenza.}
                OPTIONAL {?rent bib:DataRestituzione ?Restituzione.}
                bind( if ( exists { ?rent rdf:type bib:NoleggioAccettato },
                         "In corso" ,
                      if ( exists { ?rent rdf:type bib:NoleggioTerminato },
                         "Terminato",
                      if ( not exists { ?rent rdf:type bib:NoleggioAccettato },
                         "In attesa", "unknown status" ))) as ?Status )
                ?bookIndividual rdf:type bib:Libro.
                ?rent bib:HaOggettoNoleggio ?bookIndividual.
                ?bookIndividual bib:ISBN ?Libro.
            }
            """)),

    ADD_BOOK(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            INSERT DATA {
                ?bookUri rdf:type bib:Libro.
                ?bookUri bib:Nome ?bookName.
                ?bookUri bib:ISBN ?isbn.
                ?bookUri bib:ScrittoDa ?author.
                ?bookUri bib:ContenutoIn ?shelf.
            }
            """)),

    ADD_MEMBER(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            PREFIX foaf: <http://xmlns.com/foaf/0.1/>
            INSERT DATA {
                ?memberUri rdf:type bib:Abbonato.
                ?cardUri rdf:type bib:Tessera.
                ?memberUri bib:Possiede ?cardUri.
                ?cardUri bib:Identificativo ?id.
                ?memberUri foaf:name ?name.
                ?memberUri foaf:surname ?surname.
                ?memberUri bib:Cellulare ?phoneNumber.
                ?memberUri bib:Indirizzo ?address.
                ?memberUri bib:Email ?email.
            }""")),

    ADD_RENT(new StringQuery(
            """
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX bib: <http://www.semanticweb.org/mallo/ontologies/2023/5/biblioteca#>
            INSERT DATA {
                ?rentUri rdf:type bib:Noleggio.
                ?rentUri bib:DataAvvenimento ?date.
                ?rentUri bib:HaOggettoNoleggio ?book.
                ?rentUri bib:RichiestoDa ?member.
            }"""))
    ;

    private final StringQuery query;

    /**
     * Constructs a Queries enum constant with the provided StringQuery instance.
     *
     * @param query The StringQuery instance representing the predefined SPARQL query.
     */
    Queries(StringQuery query) {
        this.query = query;
    }

    /**
     * Gets the StringQuery instance associated with these Queries enum constant.
     *
     * @return The StringQuery instance representing the predefined SPARQL query.
     */
    public StringQuery instance() {
        return this.query;
    }
}
