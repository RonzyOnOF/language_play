package com.languageplay.factories;

import java.io.File;

import com.languageplay.products.Subtitle;

// creator
public abstract class SubtitleFactory {

    public abstract Subtitle createSubtitles(File subFile);
    
}
