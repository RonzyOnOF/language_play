package com.languageplay.products;

import java.io.File;
import java.util.ArrayList;
import com.languageplay.SubtitleLine;

// concrete product
public class AssSubtitle extends Subtitle {

    private ArrayList<SubtitleLine> subtitles;

    public AssSubtitle(File subFile) {
        super(subFile);
    }

    @Override
    public void readSubtitleFile(File subFile) {
        
    }
    
}
