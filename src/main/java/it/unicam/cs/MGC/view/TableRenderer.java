package it.unicam.cs.MGC.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A concrete implementation of the Renderer interface that renders data into a JavaFX TableView.
 *
 * <p>The TableRenderer class implements the Renderer interface to render data by converting
 * the input data into an ObservableList of maps and to populates the TableView.</p>
 *
 * @see Renderer
 */
public class TableRenderer implements Renderer<Iterator<HashMap<String, Object>>, TableView<Map>>{

    /**
     * Renders the provided data into the given JavaFX TableView.
     *
     * @param data    The data to be rendered, which is an Iterator of HashMaps with String keys and Object values.
     * @param UINode  The JavaFX TableView into which the data will be rendered.
     */
    @Override
    public void render(Iterator<HashMap<String, Object>> data, TableView<Map> UINode) {
        ObservableList<Map> dataList = FXCollections.observableArrayList();

        while(data.hasNext()) {
            HashMap<String, Object> rowData = data.next();
            dataList.add(rowData);
        }
        UINode.setItems(dataList);
    }
}
