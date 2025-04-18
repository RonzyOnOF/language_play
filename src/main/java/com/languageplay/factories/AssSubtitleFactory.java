package com.languageplay.factories;
import com.languageplay.products.AssSubtitle;
import com.languageplay.products.Subtitle;

// concrete creator
public class AssSubtitleFactory extends SubtitleFactory {

    @Override
    public Subtitle createSubtitles(double startTime, double endTime, String dialogue) {
        Subtitle subtitle = new AssSubtitle(startTime, endTime, dialogue);
        return subtitle;
    }
    
}
