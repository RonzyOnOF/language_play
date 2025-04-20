package com.languageplay;

public class SubtitleLine {
    
    private double startTime;
    private double endTime;
    private String dialogue;


    public SubtitleLine(double startTime, double endTime, String dialogue) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dialogue = dialogue;
    }

    public double getStartTime() {
        return this.startTime;
    }

    public double getEndTime() {
        return this.endTime;
    }

    public String getDialogue() {
        return this.dialogue;
    }


}
