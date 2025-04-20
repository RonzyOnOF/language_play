package com.languageplay.factories;
import java.io.File;

import com.languageplay.products.AssSubtitle;
import com.languageplay.products.Subtitle;

// concrete creator
public class AssSubtitleFactory extends SubtitleFactory {

    @Override
    public Subtitle createSubtitles(File subFile) {

        return new AssSubtitle(subFile);
        
    }
    
}
