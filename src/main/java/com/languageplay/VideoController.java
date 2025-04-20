package com.languageplay;

import java.io.File;

import com.languageplay.products.Subtitle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
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
    private StackPane mainVideoContainer;

    @FXML
    private StackPane videoWrapper;

    @FXML
    private Label videoInfoText;

    @FXML
    private HBox controlsContainer;

    @FXML
    private HBox subtitleContainer;

    @FXML
    private Button playButton;

    @FXML
    private Button rewindButton;

    @FXML
    private Button forwardButton;

    @FXML
    private Label subtitleLabel;

    private double x;
    private double y;

    private Subtitle subtitles;

    private Media videoMedia;
    private MediaView videoContainer;
    private MediaPlayer mediaPlayer;
    private File videoFile;
    private File subtitleFile;
    private Image playImage = new Image(getClass().getResource("/com/languageplay/images/play-button.png").toExternalForm());
    private Image pauseImage = new Image(getClass().getResource("/com/languageplay/images/pause.png").toExternalForm());
    private Image rewindImage = new Image(getClass().getResource("/com/languageplay/images/back.png").toExternalForm());
    private ImageView rewindImageView = new ImageView(rewindImage);
    private Image fastForwardImage = new Image(getClass().getResource("/com/languageplay/images/next-button.png").toExternalForm());
    private ImageView fastForwardImageView = new ImageView(fastForwardImage);
    private ImageView playImageView = new ImageView();

    // initialize is called when loading file, so trying to access fields like
    // videoFile and subtitleFile will be null when loaded
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

        // media playback control methods

        playButton.setOnMouseClicked(e -> {
            if (videoMedia != null) {
                if (mediaPlayer.getStatus() == Status.PLAYING) {
                    mediaPlayer.pause();
                    playImageView.setImage(playImage);
                } else {
                    mediaPlayer.play();
                    playImageView.setImage(pauseImage);
                }
            }
        });

        rewindButton.setOnMouseClicked(e -> {
            if (videoMedia != null) {
                Duration currTime = mediaPlayer.getCurrentTime();
                Duration fiveSeconds = currTime.subtract(Duration.seconds(5));
                if (fiveSeconds.lessThan(Duration.ZERO)) {
                    fiveSeconds = (Duration.ZERO);
                }
                mediaPlayer.seek(fiveSeconds);
                if (mediaPlayer.getRate() != 1.0) {
                    mediaPlayer.setRate(1.0);
                }
            }
        });

        forwardButton.setOnMouseClicked(e -> {
            if (videoMedia != null) {
                Duration currTime = mediaPlayer.getCurrentTime();
                Duration totalTime = mediaPlayer.getTotalDuration();
                if (currTime.add(Duration.seconds(5)).greaterThanOrEqualTo(totalTime)) {
                    mediaPlayer.seek(totalTime);
                } else {
                    mediaPlayer.seek(currTime.add(Duration.seconds(5)));
                }
            }
        });

        controlsContainer.setMaxHeight(50);
        subtitleContainer.setMaxHeight(100);


        StackPane.setAlignment(topSection, Pos.TOP_CENTER);
        StackPane.setAlignment(controlsContainer, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(subtitleContainer, Pos.BOTTOM_CENTER);

        playImageView.setImage(pauseImage);
        playButton.setGraphic(playImageView);

        rewindButton.setGraphic(rewindImageView);
        forwardButton.setGraphic(fastForwardImageView);

        // controlsContainer.setVisible(false);
        // controlsContainer.setDisable(true);

    }

    public void setVideoFile(File videoFile) {
        if (videoFile != null) {
            this.videoFile = videoFile;
            renderVideo(this.videoFile);
        } else {
            System.out.println("video file is null");
        }
    }

    // for now, it's ok if user doesn't drag sub file, but let them know no sub file
    // was opened or couldn't be opened
    public void setSubFile(File subtitleFile) {
        this.subtitleFile = subtitleFile;
        if (subtitleFile == null) {
            System.out.println("sub file is empty");
        }
    }

    // in the future, optimize this by doing binary search since the subtitles are sorted in ascending order
    public void renderSubs() {
        if (this.mediaPlayer != null) {
            // attach event listener to everytime the time changes on video i.e. video is playing
            mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {

                double currentTimeSeconds = newTime.toSeconds();

                // Checks to display the correct one
                for (SubtitleLine sub : subtitles.getSubtitles()) {
                    // If the current time is within the subtitle's start and end time
                    if (currentTimeSeconds >= sub.getStartTime() && currentTimeSeconds <= sub.getEndTime()) {
                        subtitleLabel.setText(sub.getDialogue()); // Show the subtitle text
                        return;
                    }
                }
                subtitleLabel.setText(""); // Clear subtitles if no match
            });
        }
    }

    // function that takes in Subtitles object which will contain arraylist of SubtitleLine objects
    public void setSubtitles(Subtitle subtitle) {
        this.subtitles = subtitle;
    }

    private void renderVideo(File videoFile) {
        if (videoFile != null) {
            videoMedia = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(videoMedia);
            videoContainer = new MediaView(mediaPlayer);
            videoContainer.setPreserveRatio(true);
            videoContainer.fitWidthProperty().bind(mainVideoContainer.widthProperty());
            videoContainer.fitHeightProperty().bind(mainVideoContainer.heightProperty());
            renderSubs();
            mediaPlayer.setAutoPlay(true);
            videoWrapper.getChildren().add(videoContainer);
        }
    }

}
