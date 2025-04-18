package com.languageplay.subtitleClasses;

import com.languageplay.utils.SubExtensions;

// this class will handle different kinds of subtitle extensions and performs various methods like re-timing and adjusting subs
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
    // public 
    

    // concrete classes


}
