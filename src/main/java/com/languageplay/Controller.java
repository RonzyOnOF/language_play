package com.languageplay;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// This file (Controller) adds logic to fxml file
public class Controller {

    @FXML
    private ToolBar topSection;
    @FXML
    private Button close_button;
    private double x;
    private double y;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void close_stage(MouseEvent event) {
        if (stage != null) {
            stage.close();
        }

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

        close_button.setOnMouseClicked(e -> {
            close_stage(e);
        });

    }

}
