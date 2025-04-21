package com.languageplay;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DefinitionWindow {
    
    @FXML
    private Label wordLabel;

    @FXML
    private Label definitionLabel;


    public void initialize() {
        // Initialize your pop-up window content here
    }


    public void setText(String word) {
        wordLabel.setText(word);
    }

    public void setDefinition(String word) {

        if (word.equals("definition not found")) {
            definitionLabel.setText("word not found");
            return;
        }

        definitionLabel.setText(word);
    }

}
