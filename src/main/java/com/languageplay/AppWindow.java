package com.languageplay;
import javafx.stage.Stage;
import javafx.stage.Screen;


public abstract class AppWindow {

    protected Stage stage;
    protected boolean isFullScreen;


    // concrete methods
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void minimizeStage() {
        if (this.stage != null) {
            stage.setIconified(true);
        }
    }

    public void closeStage() {
        if (this.stage != null) {
            stage.close();
        }
    }

    public void maximizeStage() {
        if (this.stage != null) {
            if (this.isFullScreen) {
                this.stage.setMaximized(false);
                this.isFullScreen = false;
            } else {
                this.stage.setMaximized(true);
                this.isFullScreen = true;
            }
        }
    }

    // abstract methods


}