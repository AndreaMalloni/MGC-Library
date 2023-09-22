package it.unicam.cs.MGC.model.executors;

/**
 * A generic interface for executing a computation and returning a result.
 *
 * @param <K> The type of the result returned by the execute method.
 * @param <V> The type of the input parameter for the execute method.
 */
public interface Executor<K, V> {
    /**
     * Executes a computation based on the given input parameter and returns the result.
     *
     * @param executionParam The input parameter for the computation.
     * @return The result of the computation.
     */
    public K execute(V executionParam);
}
