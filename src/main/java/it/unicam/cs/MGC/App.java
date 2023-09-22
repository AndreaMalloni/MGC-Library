package it.unicam.cs.MGC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/libraryUI.fxml")));

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Library");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}