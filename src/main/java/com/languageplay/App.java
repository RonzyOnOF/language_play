package com.languageplay;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // load fxml file and display it in the stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 500);

        scene.getStylesheets().add(getClass().getResource("/com/languageplay/styles/styles.css").toExternalForm());
        System.out.println(scene.getStylesheets());


        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}