package com.languageplay.products;

import com.languageplay.utils.SubExtensions;

import java.io.File;
import java.util.ArrayList;

import com.languageplay.SubtitleLine;

// the 'product' class
public abstract class Subtitle {

    protected File subFile;
    // array list where each element is an object of one subtitle line
    protected ArrayList<SubtitleLine> subtitles;


    public Subtitle(File subFile) {
        this.subFile = subFile;
        this.subtitles = new ArrayList<>();
    }

    public ArrayList<SubtitleLine> getSubtitles() {
        return this.subtitles;
    }


    public abstract void readSubtitleFile(File subFile);

    
}
