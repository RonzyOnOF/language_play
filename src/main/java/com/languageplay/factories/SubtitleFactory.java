package com.languageplay.factories;

import com.languageplay.products.Subtitle;

// creator
public abstract class SubtitleFactory {

    public abstract Subtitle createSubtitles(double startTime, double endTime, String dialogue);
    
}
