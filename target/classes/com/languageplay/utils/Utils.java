package com.languageplay.utils;
import java.io.File;
import java.io.InputStreamReader;


public class Utils {

    private String checkMetaData = "ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1";




    public boolean checkValidVideoFormat(File file) {
        
    }
    
}
