package com.languageplay;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

import com.languageplay.utils.VideoUtils;


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
            openVideoFile(e);
            // if (hasSubtitleFile.get()) {
            //     openVideoFile(e);
            // } else {
            //     openSubtitleFile(e);
            // }
        });

    }

    // public void openSubtitleFile(MouseEvent e) {

    //     subtitleFileExtensions = new ArrayList<>();
    //     subtitleFileExtensions.add("*.srt");
    //     subtitleFileExtensions.add("*.ass");
    //     subtitleFileExtensions.add("*.txt");

    //     try {
    //         FileChooser fileChooser = new FileChooser();
    //         fileChooser.setTitle("Open Subtitle File");

    //         fileChooser.getExtensionFilters().addAll(
    //                 new FileChooser.ExtensionFilter("srt/ass", subtitleFileExtensions));
    //         this.subtitleFile = fileChooser.showOpenDialog(this.stage);
    //         if (subtitleFile != null) {
    //             hasSubtitleFile.set(true);
    //             openFileButton.setText("Open video");
    //             System.out.println(subtitleFile.getName());
    //         }
    //     } catch (Exception exception) {
    //         System.err.println(exception);
    //     }

    // }

    public void openVideoFile(MouseEvent e) {

        videoFileExtensions = new ArrayList<>();
        videoFileExtensions.add("*.mp4");
        videoFileExtensions.add("*.mov");
        videoFileExtensions.add("*.mkv");

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Video File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("vid", videoFileExtensions));
            this.videoFile = fileChooser.showOpenDialog(this.stage);
            if (videoFile != null) {
                System.out.println(videoFile.getAbsolutePath());
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
        VideoUtils utils = new VideoUtils(this.videoFile);
        double resolution[] = utils.getVideoResolution();
        // getVideoResolution will return a 1 for the 3rd index of the return value if successfully retrieved the video's resolution
        if (resolution[2] == 1) {
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/languageplay/styles/videoSceneStyle.css").toExternalForm());
            videoController = loader.getController();
            videoController.setStage(this.stage);
            videoController.setVideoFile(this.videoFile);
            videoController.setSubFile(this.subtitleFile);
            // check to see if video's resolution is greater than user's screen in order to scale appropriately
            resolution = utils.checkScreenResolution();
            stage.setScene(scene);
            System.out.println("Width: " + resolution[0] + " Height: " + resolution[1]);
            stage.setWidth(resolution[0]);
            stage.setHeight(resolution[1]);
            stage.show();
        } else {
            System.err.println("Error retrieving video resolution");
        }

    }

}
