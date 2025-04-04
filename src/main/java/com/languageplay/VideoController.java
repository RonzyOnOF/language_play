package com.languageplay;


import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class VideoController extends AppWindow {
        
    @FXML 
    private ToolBar topSection;

    @FXML
    private Button minimize_button;

    @FXML
    private Button fullScreenButton;

    @FXML
    private Button close_button;

    @FXML
    private BorderPane mainVideoContainer;

    @FXML
    private StackPane videoWrapper;

    @FXML
    private Label videoInfoText;

    private double x;
    private double y;

    private MediaView videoContainer;
    private MediaPlayer mediaPlayer;
    private File videoFile;
    private File subtitleFile;

    // initialize is called when loading file, so trying to access fields like videoFile and subtitleFile will be null when loaded
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

        minimize_button.setOnMouseClicked(e -> {
            minimizeStage();
        });

        fullScreenButton.setOnMouseClicked(e -> {
            maximizeStage();
        });

        close_button.setOnMouseClicked(e -> {
            closeStage();
        });

    }


    public void setVideoFile(File videoFile) {
        if (videoFile != null) {
            this.videoFile = videoFile;
            renderVideo(this.videoFile);
        } else {
            System.out.println("video file is null");
        }
    }

    // for now, it's ok if user doesn't drag sub file, but let them know no sub file was opened or couldn't be opened
    public void setSubFile(File subtitleFile) {
        this.subtitleFile = subtitleFile;
        if (subtitleFile == null) {
            System.out.println("sub file is empty");
        }
    }

    private void renderVideo(File videoFile) {
        if (videoFile != null) {
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            videoContainer = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            videoWrapper.getChildren().add(videoContainer);
        }
    }


}
