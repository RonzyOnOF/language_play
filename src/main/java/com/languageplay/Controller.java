package com.languageplay;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// This file (Controller) adds logic to fxml files
public class Controller extends AppWindow {

    @FXML
    private ToolBar topSection;

    @FXML
    private Button close_button;

    @FXML
    private Button fullScreenButton;

    @FXML
    private Button minimize_button;

    @FXML
    private Button openFileButton;

    @FXML
    private VBox openFileVBox;

    @FXML
    private Button switchButton;

    private double x;
    private double y;

    // state for checking if there is a subtitle file and video file
    private BooleanProperty hasSubtitleFile = new SimpleBooleanProperty(false);
    private BooleanProperty hasVideoFile = new SimpleBooleanProperty(false);

    private File subtitleFile;
    private File videoFile;

    private List<String> subtitleFileExtensions;
    private List<String> videoFileExtensions;

    // private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    private VideoController videoController;

    public void initialize() {

        topSection.setOnMousePressed((MouseEvent e) -> {
            // getSceneX() returns x coordinate relative to the toolbar
            x = e.getSceneX();
            y = e.getSceneY();
        });

        topSection.setOnMouseDragged(e -> {
            if (stage != null) {
                this.stage.setX(e.getScreenX() - x);
                this.stage.setY(e.getScreenY() - y);
            }
        });

        close_button.setOnMouseClicked(e -> {
            closeStage();
        });

        minimize_button.setOnMouseClicked(e -> {
            minimizeStage();
        });

        fullScreenButton.setOnMouseClicked(e -> {
            maximizeStage();
        });

        openFileButton.setOnMouseClicked(e -> {
            if (hasSubtitleFile.get()) {
                openVideoFile(e);
            } else {
                openSubtitleFile(e);
            }
        });

        switchButton.setOnMouseClicked(e -> {
            try {
                switchToVideoScene();
            } catch (IOException err) {
                System.err.println(err);
            }
        });

    }

    public void openSubtitleFile(MouseEvent e) {

        subtitleFileExtensions = new ArrayList<>();
        subtitleFileExtensions.add("*.srt");
        subtitleFileExtensions.add("*.ass");
        subtitleFileExtensions.add("*.txt");

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Subtitle File");

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("srt/ass", subtitleFileExtensions));
            subtitleFile = fileChooser.showOpenDialog(this.stage);
            if (subtitleFile != null) {
                hasSubtitleFile.set(true);
                openFileButton.setText("Open video");
                System.out.println(subtitleFile.getName());
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }

    }

    public void openVideoFile(MouseEvent e) {

        videoFileExtensions = new ArrayList<>();
        videoFileExtensions.add("*.mp4");
        videoFileExtensions.add("*.mov");
        videoFileExtensions.add("*.mkv");

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Video File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("vid", videoFileExtensions));
            videoFile = fileChooser.showOpenDialog(this.stage);
            if (videoFile != null) {
                System.out.println(videoFile.getName());
                hasVideoFile.set(true);
                try {
                    switchToVideoScene();
                } catch (IOException err) {
                    System.err.println(err);
                }
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }

    }

    public void switchToVideoScene() throws IOException {
        loader = new FXMLLoader(getClass().getResource("videoScene.fxml"));
        root = loader.load();
        scene = new Scene(root, 900, 500);
        scene.getStylesheets().add(getClass().getResource("/com/languageplay/styles/styles.css").toExternalForm());
        videoController = loader.getController();
        videoController.setStage(this.stage);
        stage.setScene(scene);
        stage.show();
    }

}
