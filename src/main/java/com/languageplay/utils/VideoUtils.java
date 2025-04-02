package com.languageplay.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class VideoUtils {

    private StringBuilder checkResolutionCommand = new StringBuilder("ffprobe -v error -select_streams v:0 -show_entries stream=width,height -of csv=s=x:p=0 "); 
    private StringBuilder checkMetaDataCommand = new StringBuilder("ffprobe -v quiet -show_entries format_tags=major_brand,encoder -of default=noprint_wrappers=1 ");
    ProcessBuilder pb;
    private File videoFile;
    private String fileName;
    

    public VideoUtils(File file) {
        if (file != null) {
            this.videoFile = file;
            this.fileName = file.getName();            
            this.checkResolutionCommand.append(file.getAbsolutePath());
            this.checkMetaDataCommand.append(file.getAbsolutePath());
        }
    }


    public void getVideoResolution() {

        String os = System.getProperty("os.name").toLowerCase();
        String command = checkResolutionCommand.toString();
        String[] commandArray = command.split(" ");  // Split by spaces
        // check if on windows or unix based systems and performs appropriate commands
        if (os.contains("win")) {
            pb = new ProcessBuilder("cmd", "/c", String.join(" ", commandArray));
        } else {
            pb = new ProcessBuilder("bash", "-c", String.join(" ", commandArray));
        }

        // convert the bottom code into some method
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    // public boolean checkValidVideoFormat(File file) {



    // }

    // public void 
    
}
