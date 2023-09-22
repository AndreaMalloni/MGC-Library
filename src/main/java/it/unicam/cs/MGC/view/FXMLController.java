package it.unicam.cs.MGC.view;

import it.unicam.cs.MGC.*;
import it.unicam.cs.MGC.controller.AppController;
import it.unicam.cs.MGC.model.executors.QueryExecutor;
import it.unicam.cs.MGC.model.ontology.Builder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyCode;
import openllet.jena.PelletReasonerFactory;
import org.apache.jena.rdf.model.Model;

import java.util.*;

public class FXMLController {
    private AppController controller;
    private Renderer<Iterator<HashMap<String, Object>>, TableView<Map>> tableRenderer = new TableRenderer();

    @FXML
    private TableView<Map> bookTable;

    @FXML
    private TableView<Map> memberTable;

    @FXML
    private TableView<Map> rentingTable;

    @FXML
    private Button addBookButton;

    @FXML
    private Button addMemberButton;

    @FXML
    private Button addRentingButton;

    @FXML
    private TextField bookNameTextFilter;

    @FXML
    private TextField memberNameTextFilter;

    @FXML
    private TextField memberPhoneTextFilter;

    @FXML
    private Button removeBookButton;

    @FXML
    private Button removeMemberButton;

    @FXML
    private Button removeRentingButton;

    @FXML
    private TextField rentingStatusTextFilter;

    @FXML
    private TextField requestDateTextFilter;

    @FXML
    private TextField sectionTextFilter;

    @FXML
    private TextField shelfTextFilter;

    @FXML
    void initialize() {
        Model ontologyModel =  new Builder().build("library.owl", PelletReasonerFactory.theInstance().create());
        this.controller = new AppController(ontologyModel, new QueryExecutor(ontologyModel));

        initCatalogTab();
        initMemberTab();
        initRentingTab();

        tableRenderer.render(controller.getBooks(), bookTable);
        tableRenderer.render(controller.getMembers(), memberTable);
        tableRenderer.render(controller.getRents(), rentingTable);
    }

    void initCatalogTab() {
        initTable(this.bookTable);
        setupTextFieldListener(this.bookNameTextFilter, this.bookNameTextFilter, sectionTextFilter, shelfTextFilter);
        setupTextFieldListener(this.sectionTextFilter, this.sectionTextFilter, this.bookNameTextFilter);
        setupTextFieldListener(this.shelfTextFilter, this.shelfTextFilter, this.bookNameTextFilter);
        setupEnterKeyPressedEventForTextFields(this.bookNameTextFilter, this.sectionTextFilter, this.shelfTextFilter);
        setupTableListener(this.bookTable, this.addBookButton, this.removeBookButton);
    }

    void initMemberTab() {
        initTable(this.memberTable);
        setupTextFieldListener(this.memberNameTextFilter, this.memberNameTextFilter, this.memberPhoneTextFilter);
        setupTextFieldListener(this.memberPhoneTextFilter, this.memberPhoneTextFilter, this.memberNameTextFilter);
        setupEnterKeyPressedEventForTextFields(this.memberNameTextFilter, this.memberPhoneTextFilter);
        setupTableListener(this.memberTable, this.addMemberButton, this.removeMemberButton);
    }

    void initRentingTab() {
        initTable(this.rentingTable);
        setupTextFieldListener(this.rentingStatusTextFilter, this.rentingStatusTextFilter, this.requestDateTextFilter);
        setupTextFieldListener(this.requestDateTextFilter, this.requestDateTextFilter, this.rentingStatusTextFilter);
        setupEnterKeyPressedEventForTextFields(this.rentingStatusTextFilter, this.requestDateTextFilter);
        setupTableListener(this.rentingTable, this.addRentingButton, this.removeRentingButton);
    }

    private boolean isTextFieldContentNumeric(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setupTextFieldListener(TextField textField, TextField activeTextField, TextField... disabledTextFields) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(newValue, "")) {
                filterSwitch(activeTextField, disabledTextFields);
            } else {
                filterReset(disabledTextFields);
            }
        });
    }

    private <T> void setupTableListener(TableView<T> table, Button addButton, Button removeButton) {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (removeButton.isDisabled()) {
                    buttonSwitch(removeButton, addButton);
                }
            } else {
                addButton.setDisable(false);
                removeButton.setDisable(true);
            }
        });
    }

    private void setupEnterKeyPressedEventForTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER && (!Objects.equals(textField.getText(), ""))) {
                    updateTableBasedOnTextField(textField);
                }
            });
        }
    }

    private void updateTableBasedOnTextField(TextField textField) {
        if (textField == bookNameTextFilter) {
            tableRenderer.render(this.controller.getBooks(textField.getText()), bookTable);
        } else if (textField == sectionTextFilter || textField == shelfTextFilter) {
            String sectionText = sectionTextFilter.getText();
            String shelfText = shelfTextFilter.getText();
            if (isTextFieldContentNumeric(sectionText) && isTextFieldContentNumeric(shelfText) && sectionText.length() == 3 && shelfText.length() == 2) {
                tableRenderer.render(this.controller.getBooks(Integer.parseInt(sectionText), Integer.parseInt(shelfText)), this.bookTable);
            }
        } else if (textField == memberNameTextFilter) {
            tableRenderer.render(this.controller.getMembers(textField.getText()), this.memberTable);
        } else if (textField == memberPhoneTextFilter && isTextFieldContentNumeric(textField.getText())) {
            tableRenderer.render(this.controller.getMembers(Long.parseLong(textField.getText())), this.memberTable);
        } else if (textField == rentingStatusTextFilter) {
            tableRenderer.render(this.controller.getRents(textField.getText()), this.rentingTable);
        } else if (textField == requestDateTextFilter) {
            tableRenderer.render(this.controller.getRentsByDate(textField.getText()), this.rentingTable);
        }
    }

    void buttonSwitch(Button first, Button second) {
        first.setDisable(!first.isDisabled());
        second.setDisable(!first.isDisabled());
    }

    void filterSwitch(TextField active, TextField ... fields) {
        active.setDisable(false);
        for(TextField field : fields) {
            field.setDisable(true);
        }
    }

    void filterReset(TextField ... fields) {
        for(TextField field : fields) {
            field.setDisable(false);
        }
    }

    void initTable(TableView<Map> table) {
        for (TableColumn<Map, ?> column : table.getColumns()) {
            column.setCellValueFactory(new MapValueFactory<>(column.getText()));
        }
    }
}
