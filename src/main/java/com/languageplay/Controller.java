package com.languageplay;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// This file (Controller) adds logic to fxml file
public class Controller {

    @FXML
    private ToolBar topSection;

    private double x;
    private double y;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initialize() {

        topSection.setOnMousePressed((MouseEvent e) -> {
            // getSceneX() returns x coordinate relative to the toolbar
            x = e.getSceneX();
            y = e.getSceneY();
        });

        topSection.setOnMouseDragged(e -> {
            if (stage != null) {
                stage.setX(e.getScreenX() - x);
                stage.setY(e.getScreenY() - y);
            }
        });
    }


}
