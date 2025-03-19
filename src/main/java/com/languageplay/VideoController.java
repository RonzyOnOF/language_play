package com.languageplay;


import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;



public class VideoController extends AppWindow {
        
    @FXML 
    ToolBar topSection;

    @FXML
    Button minimize_button;

    @FXML
    Button close_button;



    public void initialize() {

        minimize_button.setOnMouseClicked(e -> {
            minimizeStage();
        });

        close_button.setOnMouseClicked(e -> {
            closeStage();
        });

    }



}
