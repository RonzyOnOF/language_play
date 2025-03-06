package com.languageplay;

import javafx.application.Platform;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.*;

// This file (Controller) adds logic to fxml filea
public class Controller {

    @FXML
    private ToolBar topSection;

    @FXML
    private Button close_button;

    @FXML
    private Button minimize_button;

    @FXML
    private Button openFileButton;

    private double x;
    private double y;
    private boolean hasSubtitleFile = false;
    private boolean hasVideoFile = false;

    private List<String> subtitleFileExtensions;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void close_stage(MouseEvent event) {
        if (stage != null) {
            stage.close();
        }

    }

    public void minimizeStage() {
        if (stage != null) {
            this.stage.setIconified(true);
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

        minimize_button.setOnMouseClicked(e -> {
            minimizeStage();
        });

        openFileButton.setOnMouseClicked(e -> {
            openFile(e);
        });

    }

    public void openFile(MouseEvent e) {
        Button targetButton = (Button) e.getSource();

        subtitleFileExtensions = new ArrayList<>();
        subtitleFileExtensions.add("*.srt");
        subtitleFileExtensions.add("*.ass");

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Subtitle File");
    
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("srt/ass", subtitleFileExtensions));
            File subtitleFile = fileChooser.showOpenDialog(this.stage);
            if (subtitleFile != null) {
                hasSubtitleFile = true;
                System.out.println(subtitleFile.getName());
            }
        } catch (Exception exception) {
            System.err.println(e);
        }

    }

}
