package it.unicam.cs.MGC.model.parsers;

/**
 * A generic interface for parsing data of type V and converting it to a result of type K.
 *
 * <p>The Parser interface defines a generic contract for parsing data and producing a result
 * of a different type. Implementing classes should specify how to perform the parsing process
 * for the given data type V and return the result of type K.</p>
 *
 * @param <K> The type of the result returned by the parse method.
 * @param <V> The type of the data to be parsed by the parse method.
 */
public interface Parser<K, V> {

    /**
     * Parses the given data of type V and produces a result of type K.
     *
     * @param data The data to be parsed.
     * @return The parsed result of type K.
     */
    public K parse(V data);
}
