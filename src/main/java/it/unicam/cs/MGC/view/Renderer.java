package it.unicam.cs.MGC.view;

import javafx.scene.Node;

/**
 * An interface that defines the rendering of data into any JavaFX node.
 *
 * @param <K> the type of data to be rendered.
 * @param <V> the node type where the data need to be rendered. Can also be a subclass of Node.
 */
public interface Renderer<K, V extends Node> {

    /**
     * Renders the given data into the given node (Must be a JavaFX node).
     *
     * @param data the data to be rendered.
     * @param UINode the specific node where the data need to be rendered.
     */
    public void render(K data, V UINode);
}
