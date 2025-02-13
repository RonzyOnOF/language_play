package com.languageplay;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

// This file (Controller) adds logic to fxml file
public class Controller {

    @FXML
    private Label hero_text;

    
    public void initialize() {
        hero_text.setText("Language Play");
    }

}
