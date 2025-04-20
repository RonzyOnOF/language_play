package com.languageplay.factories;

import java.io.File;

import com.languageplay.products.SrtSubtitle;
import com.languageplay.products.Subtitle;

// concrete creator
public class SrtSubtitleFactory extends SubtitleFactory {

    @Override
    public Subtitle createSubtitles(File subFile) {

        return new SrtSubtitle(subFile);
        
    }
    
}
