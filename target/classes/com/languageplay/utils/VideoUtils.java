package com.languageplay.utils;
import java.io.File;
import java.io.InputStreamReader;


public class VideoUtils {

    // private String checkMetaData = "ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1";
    private StringBuffer checkMetaDataCommand;
    private File videoFile;
    private String fileName;
    

    public VideoUtils(File file) {
        if (file != null) {
            this.videoFile = file;
            this.fileName = this.videoFile.getName();            
            this.checkMetaDataCommand = "ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1" + "'" + fileName + "'";
        }
    }


    public boolean checkValidVideoFormat(File file) {
        
    }

    public void 
    
}
