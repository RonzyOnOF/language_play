package com.languageplay.products;

import com.languageplay.utils.SubExtensions;

// the 'product' class
public abstract class Subtitle {

    private SubExtensions subFormat;

    private double startTime;
    private double endTime;
    private String dialogue;


    public Subtitle(double startTime, double endTime, String dialogue) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dialogue = dialogue;
    }


    // abstract methods
    

    // concrete classes


}
